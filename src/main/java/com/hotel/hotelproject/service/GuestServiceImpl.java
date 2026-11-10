package com.hotel.hotelproject.service;

import com.hotel.hotelproject.dto.GuestDTO;
import com.hotel.hotelproject.exception.BusinessRuleException;
import com.hotel.hotelproject.exception.ResourceNotFoundException;
import com.hotel.hotelproject.model.Guest;
import com.hotel.hotelproject.repository.GuestRepository;
import com.hotel.hotelproject.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional public class GuestServiceImpl implements GuestService { private final GuestRepository repo; private final ReservationRepository reservations; private Guest e(Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Guest "+id+" not found"));} private GuestDTO d(Guest x){return new GuestDTO(x.getId(),x.getName(),x.getEmail(),x.getPhone());} public List<GuestDTO> findAll(){return repo.findAll().stream().map(this::d).toList();} public GuestDTO findById(Long id){return d(e(id));} public GuestDTO save(GuestDTO d){Guest x=d.id()==null?new Guest():e(d.id());x.setName(d.name());x.setEmail(d.email());x.setPhone(d.phone());return d(repo.save(x));} public void delete(Long id){if(reservations.existsByGuestId(id))throw new BusinessRuleException("Cannot delete guest with reservations");repo.delete(e(id));} }

