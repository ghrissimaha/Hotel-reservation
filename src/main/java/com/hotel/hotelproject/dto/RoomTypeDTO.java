package com.hotel.hotelproject.dto;
import jakarta.validation.constraints.*; import java.math.BigDecimal;
public record RoomTypeDTO(Long id,@NotBlank String name,@NotBlank String description,@NotNull @Positive BigDecimal basePrice,@NotNull @Positive Integer maxOccupancy) { public RoomTypeDTO(Long i,String n,BigDecimal p,String d){this(i,n,d,p,1);} public BigDecimal nightlyRate(){return basePrice;} }
