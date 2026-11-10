package com.hotel.hotelproject.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Reservation {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private LocalDate checkInDate;
 @Column(nullable=false) private LocalDate checkOutDate;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ReservationStatus status=ReservationStatus.PENDING;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal totalPrice;
 @Column(nullable=false) private LocalDateTime createdAt;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="guest_id",nullable=false) private Guest guest;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="room_id",nullable=false) private Room room;
 @OneToOne(mappedBy="reservation",cascade=CascadeType.ALL,orphanRemoval=true) private Payment payment;
 @PrePersist void created(){if(createdAt==null)createdAt=LocalDateTime.now();}
 public LocalDate getCheckIn(){return checkInDate;} public void setCheckIn(LocalDate v){checkInDate=v;}
 public LocalDate getCheckOut(){return checkOutDate;} public void setCheckOut(LocalDate v){checkOutDate=v;}
}
