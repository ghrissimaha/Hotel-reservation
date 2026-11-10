package com.hotel.hotelproject.service;

import com.hotel.hotelproject.dto.RoomDTO;
import com.hotel.hotelproject.exception.BusinessRuleException;
import com.hotel.hotelproject.exception.ResourceNotFoundException;
import com.hotel.hotelproject.model.*;
import com.hotel.hotelproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {
    private final RoomRepository repo;
    private final RoomTypeRepository types;
    private final EmployeeRepository employees;
    private final ReservationRepository reservations;

    public List<RoomDTO> findAll() { return repo.findAll().stream().map(this::dto).toList(); }
    public RoomDTO findById(Long id) { return dto(entity(id)); }
    public RoomDTO save(RoomDTO d) {
        Room r = d.id() == null ? new Room() : entity(d.id());
        r.setNumber(d.number()); r.setStatus(d.status());
        r.setRoomType(types.findById(d.roomTypeId()).orElseThrow(() -> new ResourceNotFoundException("Room type not found")));
        r.setAssignedEmployee(d.assignedEmployeeId() == null ? null :
                employees.findById(d.assignedEmployeeId()).orElseThrow(() -> new ResourceNotFoundException("Employee not found")));
        return dto(repo.save(r));
    }
    public void delete(Long id) { repo.delete(entity(id)); }
    public List<RoomDTO> findAvailable(LocalDate in, LocalDate out) {
        if (in == null || out == null || !out.isAfter(in)) throw new BusinessRuleException("Valid check-in and check-out dates are required");
        var active = List.of(ReservationStatus.CONFIRMED, ReservationStatus.CHECKED_IN);
        return repo.findAll().stream().filter(r -> r.getStatus() == RoomStatus.AVAILABLE)
                .filter(r -> !reservations.existsByRoomIdAndStatusInAndCheckInDateLessThanAndCheckOutDateGreaterThan(r.getId(), active, out, in))
                .map(this::dto).toList();
    }
    public RoomDTO assignEmployee(Long roomId, Long employeeId) {
        Room room = entity(roomId);
        room.setAssignedEmployee(employees.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found")));
        return dto(room);
    }
    private Room entity(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room " + id + " not found")); }
    private RoomDTO dto(Room r) { return new RoomDTO(r.getId(), r.getNumber(), r.getStatus(), r.getRoomType().getId(), r.getAssignedEmployee() == null ? null : r.getAssignedEmployee().getId()); }
}
