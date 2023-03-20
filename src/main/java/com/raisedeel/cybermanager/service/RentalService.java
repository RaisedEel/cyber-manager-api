package com.raisedeel.cybermanager.service;

import com.raisedeel.cybermanager.dto.RentalDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RentalService {

  RentalDto getRental(Long id);

  List<RentalDto> getRentalsByDate(LocalDate rentDate);

  RentalDto startRental(RentalDto rentalDto);

  RentalDto setRental(Long id, RentalDto rentalDto);

  RentalDto endRental(Long id, Date endTime);

  void removeRental(Long id);

}
