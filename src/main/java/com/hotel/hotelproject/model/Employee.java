package com.hotel.hotelproject.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Employee
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 80) private String firstName;
    @Column(nullable = false, length = 80) private String lastName;
    @Column(nullable = false, unique = true, length = 180) private String email;
    @Column(nullable = false, length = 30) private String phone;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 30) private EmployeeRole role;
    @Column(nullable = false) private LocalDate hireDate;
    @Column(nullable = false, precision = 10, scale = 2) private BigDecimal salary;
    @OneToMany(mappedBy = "employee") private List<Room> rooms = new ArrayList<>();
    public String getName(){return firstName;} public void setName(String v){firstName=v;}
}
