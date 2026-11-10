package com.hotel.hotelproject.service;

import com.hotel.hotelproject.dto.EmployeeDTO;
import com.hotel.hotelproject.dto.RoomDTO;
import com.hotel.hotelproject.exception.ResourceNotFoundException;
import com.hotel.hotelproject.model.Employee;
import com.hotel.hotelproject.repository.EmployeeRepository;
import com.hotel.hotelproject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repo;
    private final RoomRepository rooms;
    public List<EmployeeDTO> findAll() { return repo.findAll().stream().map(this::dto).toList(); }
    public EmployeeDTO findById(Long id) { return dto(entity(id)); }
    public EmployeeDTO save(EmployeeDTO d) {
        Employee e = d.id() == null ? new Employee() : entity(d.id());
        e.setName(d.name()); e.setEmail(d.email()); e.setRole(d.role());
        return dto(repo.save(e));
    }
    public void delete(Long id) {
        Employee employee = entity(id);
        rooms.findAll().stream().filter(r -> employee.equals(r.getAssignedEmployee()))
                .forEach(r -> r.setAssignedEmployee(null));
        repo.delete(employee);
    }
    public List<RoomDTO> rooms(Long id) {
        entity(id);
        return rooms.findAll().stream().filter(r -> r.getAssignedEmployee() != null
                && r.getAssignedEmployee().getId().equals(id))
                .map(r -> new RoomDTO(r.getId(), r.getNumber(), r.getStatus(), r.getRoomType().getId(), id))
                .toList();
    }
    private Employee entity(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee " + id + " not found")); }
    private EmployeeDTO dto(Employee e) { return new EmployeeDTO(e.getId(), e.getName(), e.getEmail(), e.getRole()); }
}
