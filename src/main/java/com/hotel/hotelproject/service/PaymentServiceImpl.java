package com.hotel.hotelproject.service;

import com.hotel.hotelproject.dto.PaymentDTO;
import com.hotel.hotelproject.exception.BusinessRuleException;
import com.hotel.hotelproject.exception.ResourceNotFoundException;
import com.hotel.hotelproject.model.*;
import com.hotel.hotelproject.repository.PaymentRepository;
import com.hotel.hotelproject.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repo;
    private final ReservationRepository reservations;
    public List<PaymentDTO> findAll() { return repo.findAll().stream().map(this::dto).toList(); }
    public PaymentDTO findById(Long id) { return dto(entity(id)); }
    public PaymentDTO byReservation(Long id) {
        return repo.findAll().stream().filter(p -> p.getReservation().getId().equals(id))
                .findFirst().map(this::dto)
                .orElseThrow(() -> new ResourceNotFoundException("Payment for reservation " + id + " not found"));
    }
    public PaymentDTO save(PaymentDTO d) {
        Payment p = d.id() == null ? new Payment() : entity(d.id());
        Reservation r = reservations.findById(d.reservationId())
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
        if (d.id() == null && repo.existsByReservationId(r.getId()))
            throw new BusinessRuleException("Reservation already has a payment");
        p.setAmount(d.amount()); p.setMethod(d.method()); p.setStatus(d.status());
        p.setPaidAt(d.paidAt() == null ? LocalDateTime.now() : d.paidAt()); p.setReservation(r);
        return dto(repo.save(p));
    }
    public void delete(Long id) { repo.delete(entity(id)); }
    private Payment entity(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payment " + id + " not found")); }
    private PaymentDTO dto(Payment p) { return new PaymentDTO(p.getId(), p.getAmount(), p.getStatus(), p.getMethod(), p.getPaidAt(), p.getReservation().getId()); }
}
