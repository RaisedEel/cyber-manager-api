package com.raisedeel.cybermanager.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
public class ComputerDto {
  private long id;

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
  @NotBlank(message = "Brand cannot be blank")
  @Size(max = 60, message = "Brand cannot be bigger than 60 characters")
  private String brand;

  @NonNull
  @NotNull(message = "Antiquity years cannot be empty")
  @Min(value = 0, message = "Antiquity years cannot be smaller than 0")
  @Max(value = 99, message = "Antiquity years cannot be bigger than 99")
  private int antiquity;

  @NonNull
  @NotNull(message = "Revision date cannot be empty")
  @PastOrPresent(message = "Revision date must be in the present or the past")
  private Date revision;

  @NonNull
  @NotBlank(message = "Serial cannot be blank")
  @Size(max = 60, message = "Serial cannot be bigger than 60 characters")
  private String serial;

  @NonNull
  @NotBlank(message = "Description cannot be blank")
  @Size(max = 200, message = "Description cannot be bigger than 200 characters")
  private String description;
}
