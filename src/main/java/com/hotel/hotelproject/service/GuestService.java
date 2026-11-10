package com.hotel.hotelproject.service; import com.hotel.hotelproject.dto.*; import java.util.*;  public interface GuestService { List<GuestDTO> findAll(); GuestDTO findById(Long id); GuestDTO save(GuestDTO dto); void delete(Long id); }

