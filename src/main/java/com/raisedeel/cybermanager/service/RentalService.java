package com.raisedeel.cybermanager.service;

import com.raisedeel.cybermanager.dto.RentalDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RentalService {

  RentalDto getRental(Long id);

  List<RentalDto> getRentalsByDate(LocalDate rentDate);

  RentalDto startRental(Long computerId, RentalDto rentalDto);

  RentalDto setRental(Long id, RentalDto rentalDto);

  RentalDto endRental(Long id, LocalDateTime endTime);

  void removeRental(Long id);

}
