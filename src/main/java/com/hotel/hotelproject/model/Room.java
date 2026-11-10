package com.hotel.hotelproject.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true, length = 20) private String roomNumber;
    @Column(nullable = false) private int floor;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private RoomStatus status = RoomStatus.AVAILABLE;
    @ManyToOne(optional = false, fetch = FetchType.LAZY) @JoinColumn(name = "room_type_id", nullable = false) private RoomType roomType;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "employee_id") private Employee employee;
    @OneToMany(mappedBy = "room") private List<Reservation> reservations = new ArrayList<>();
    public String getNumber(){return roomNumber;} public void setNumber(String v){roomNumber=v;}
    public Employee getAssignedEmployee(){return employee;} public void setAssignedEmployee(Employee v){employee=v;}
}
