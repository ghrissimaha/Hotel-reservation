package com.hotel.hotelproject.dto;
import com.hotel.hotelproject.model.EmployeeRole; import jakarta.validation.constraints.*; import java.math.BigDecimal; import java.time.LocalDate;
public record EmployeeDTO(Long id,@NotBlank String firstName,@NotBlank String lastName,@NotBlank @Email String email,@NotBlank String phone,@NotNull EmployeeRole role,@NotNull LocalDate hireDate,@NotNull @Positive BigDecimal salary) { public EmployeeDTO(Long i,String n,String e,EmployeeRole r){this(i,n,"",e,"",r,LocalDate.now(),BigDecimal.ZERO);} public String name(){return firstName;} }
