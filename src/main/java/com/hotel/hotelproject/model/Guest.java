package com.hotel.hotelproject.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Guest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 80) private String firstName;
    @Column(nullable = false, length = 80) private String lastName;
    @Column(nullable = false, unique = true, length = 180) private String email;
    @Column(nullable = false, length = 30) private String phone;
    @Column(length = 80) private String nationality;
    @Column(nullable = false) private LocalDateTime createdAt;
    @OneToMany(mappedBy = "guest") private List<Reservation> reservations = new ArrayList<>();
    @PrePersist void created() { if (createdAt == null) createdAt = LocalDateTime.now(); }
    public String getName(){return firstName;} public void setName(String v){firstName=v;}
}
