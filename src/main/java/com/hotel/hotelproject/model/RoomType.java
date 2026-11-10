package com.hotel.hotelproject.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RoomType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true, length = 80) private String name;
    @Column(nullable = false, length = 500) private String description;
    @Column(nullable = false, precision = 10, scale = 2) private BigDecimal basePrice;
    @Column(nullable = false) private int maxOccupancy;
    @OneToMany(mappedBy = "roomType") private List<Room> rooms = new ArrayList<>();
    public BigDecimal getNightlyRate(){return basePrice;} public void setNightlyRate(BigDecimal v){basePrice=v;}
}
