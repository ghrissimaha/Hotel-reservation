package com.hotel.hotelproject.service; import com.hotel.hotelproject.dto.*; import java.util.*;  public interface RoomTypeService { List<RoomTypeDTO> findAll(); RoomTypeDTO findById(Long id); RoomTypeDTO save(RoomTypeDTO dto); void delete(Long id); }

