package com.hotel.hotelproject.service;
import com.hotel.hotelproject.dto.*;
import java.util.*;
public interface EmployeeService { List<EmployeeDTO> findAll(); EmployeeDTO findById(Long id); EmployeeDTO save(EmployeeDTO dto); void delete(Long id); List<RoomDTO> rooms(Long id); }
