package com.hotel.hotelproject.service;

import com.hotel.hotelproject.dto.RoomTypeDTO;
import com.hotel.hotelproject.exception.BusinessRuleException;
import com.hotel.hotelproject.exception.ResourceNotFoundException;
import com.hotel.hotelproject.model.RoomType;
import com.hotel.hotelproject.repository.RoomRepository;
import com.hotel.hotelproject.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional public class RoomTypeServiceImpl implements RoomTypeService { private final RoomTypeRepository repo; private final RoomRepository rooms; private RoomType e(Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Room type "+id+" not found"));} private RoomTypeDTO d(RoomType x){return new RoomTypeDTO(x.getId(),x.getName(),x.getNightlyRate(),x.getDescription());} public List<RoomTypeDTO> findAll(){return repo.findAll().stream().map(this::d).toList();} public RoomTypeDTO findById(Long id){return d(e(id));} public RoomTypeDTO save(RoomTypeDTO d){RoomType x=d.id()==null?new RoomType():e(d.id());x.setName(d.name());x.setNightlyRate(d.nightlyRate());x.setDescription(d.description());return d(repo.save(x));} public void delete(Long id){if(rooms.existsByRoomTypeId(id))throw new BusinessRuleException("Cannot delete room type while rooms exist");repo.delete(e(id));} }

