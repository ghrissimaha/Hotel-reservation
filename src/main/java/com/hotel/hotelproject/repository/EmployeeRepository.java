package com.hotel.hotelproject.repository;
import com.hotel.hotelproject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRepository extends JpaRepository<Employee,Long> { }
