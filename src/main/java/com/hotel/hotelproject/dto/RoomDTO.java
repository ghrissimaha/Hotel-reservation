package com.hotel.hotelproject.dto;
import com.hotel.hotelproject.model.RoomStatus; import jakarta.validation.constraints.*;
public record RoomDTO(Long id,@NotBlank String roomNumber,@NotNull Integer floor,@NotNull RoomStatus status,@NotNull Long roomTypeId,Long employeeId) { public RoomDTO(Long i,String n,RoomStatus s,Long t,Long e){this(i,n,0,s,t,e);} public String number(){return roomNumber;} public Long assignedEmployeeId(){return employeeId;} }
