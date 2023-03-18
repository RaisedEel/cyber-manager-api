package com.raisedeel.cybermanager.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class RentalDto {

  private Long id;

  @NonNull
  @NotBlank(message = "Name cannot be blank")
  @Size(max = 60, message = "Name cannot be bigger than 60 characters")
  private String name;

  @NonNull
  @NotNull(message = "Price cannot be empty")
  @Min(value = 1, message = "Price cannot be smaller than $1")
  @Max(value = 100, message = "Price cannot be bigger than $100")
  private int price;

  @NonNull
  @NotNull(message = "Start time for the rent cannot be empty")
  @PastOrPresent(message = "Start time for the rent must be in the present or the past")
  private LocalTime startTime;

  private LocalTime endTime;

  @Max(value = 99, message = "Rented hours cannot be bigger than 99 hours")
  @Min(value = 0, message = "Rented hours cannot be smaller than 0 hours")
  private int rentedHours = 0;

  private int total;
}
