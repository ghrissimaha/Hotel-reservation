package com.hotel.hotelproject.service;

import com.hotel.hotelproject.dto.ReservationDTO;
import com.hotel.hotelproject.exception.BusinessRuleException;
import com.hotel.hotelproject.exception.ResourceNotFoundException;
import com.hotel.hotelproject.model.*;
import com.hotel.hotelproject.repository.GuestRepository;
import com.hotel.hotelproject.repository.ReservationRepository;
import com.hotel.hotelproject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional public class ReservationServiceImpl implements ReservationService { private final ReservationRepository repo; private final RoomRepository rooms; private final GuestRepository guests; private Reservation e(Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Reservation "+id+" not found"));} private Room room(Long id){return rooms.findById(id).orElseThrow(()->new ResourceNotFoundException("Room "+id+" not found"));} private Guest guest(Long id){return guests.findById(id).orElseThrow(()->new ResourceNotFoundException("Guest "+id+" not found"));} private ReservationDTO d(Reservation x){return new ReservationDTO(x.getId(),x.getCheckIn(),x.getCheckOut(),x.getStatus(),x.getRoom().getId(),x.getGuest().getId());} public List<ReservationDTO> findAll(){return repo.findAll().stream().map(this::d).toList();} public ReservationDTO findById(Long id){return d(e(id));} public ReservationDTO save(ReservationDTO d){if(!d.checkOut().isAfter(d.checkIn()))throw new BusinessRuleException("Check-out must be after check-in");if(d.id()==null&&d.status()==ReservationStatus.CANCELLED)throw new BusinessRuleException("New reservations cannot be cancelled");Room r=room(d.roomId());if(r.getStatus()==RoomStatus.MAINTENANCE)throw new BusinessRuleException("Room is under maintenance");var ignore=d.id()==null?-1L:d.id();var active=List.of(ReservationStatus.CONFIRMED,ReservationStatus.CHECKED_IN);boolean overlap=d.id()==null?repo.existsByRoomIdAndStatusInAndCheckInDateLessThanAndCheckOutDateGreaterThan(r.getId(),active,d.checkOut(),d.checkIn()):repo.existsByRoomIdAndIdNotAndStatusInAndCheckInDateLessThanAndCheckOutDateGreaterThan(r.getId(),d.id(),active,d.checkOut(),d.checkIn());if(overlap&&ignore<0)throw new BusinessRuleException("Room is not available for selected dates");Reservation x=d.id()==null?new Reservation():e(d.id());x.setCheckIn(d.checkIn());x.setCheckOut(d.checkOut());x.setStatus(d.status());x.setRoom(r);x.setGuest(guest(d.guestId()));return d(repo.save(x));} public List<ReservationDTO> byGuest(Long id){if(!guests.existsById(id))throw new ResourceNotFoundException("Guest "+id+" not found");return repo.findByGuestId(id).stream().map(this::d).toList();} public ReservationDTO cancel(Long id){Reservation x=e(id);if(x.getStatus()==ReservationStatus.CHECKED_OUT)throw new BusinessRuleException("Checked-out reservation cannot be cancelled");x.setStatus(ReservationStatus.CANCELLED);return d(repo.save(x));} public void delete(Long id){repo.delete(e(id));} }

