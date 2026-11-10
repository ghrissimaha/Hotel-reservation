package com.hotel.hotelproject.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Payment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal amount;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private PaymentMethod paymentMethod;
 @Column(nullable=false) private LocalDateTime paymentDate;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private PaymentStatus status=PaymentStatus.PENDING;
 @OneToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="reservation_id",nullable=false,unique=true) private Reservation reservation;
 public String getMethod(){return paymentMethod.name();} public void setMethod(String v){paymentMethod=PaymentMethod.valueOf(v);} public void setMethod(PaymentMethod v){paymentMethod=v;}
 public LocalDateTime getPaidAt(){return paymentDate;} public void setPaidAt(LocalDateTime v){paymentDate=v;}
}
