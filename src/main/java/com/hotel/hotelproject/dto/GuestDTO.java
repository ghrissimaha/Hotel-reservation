package com.hotel.hotelproject.dto;
import jakarta.validation.constraints.*;
public record GuestDTO(Long id,@NotBlank String firstName,@NotBlank String lastName,@NotBlank @Email String email,@NotBlank String phone,String nationality) { public GuestDTO(Long i,String n,String e,String p){this(i,n,"",e,p,null);} public String name(){return firstName;} }
