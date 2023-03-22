package com.raisedeel.cybermanager.service;

import com.raisedeel.cybermanager.dto.RentalDto;
import com.raisedeel.cybermanager.dto.mapper.RentalMapper;
import com.raisedeel.cybermanager.exception.ComputerNotFoundException;
import com.raisedeel.cybermanager.exception.EndTimeBeforeStartTimeException;
import com.raisedeel.cybermanager.exception.RentalNotFoundException;
import com.raisedeel.cybermanager.model.Computer;
import com.raisedeel.cybermanager.model.Rental;
import com.raisedeel.cybermanager.repository.ComputerRepository;
import com.raisedeel.cybermanager.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
@Service
public class RentalServiceImpl implements RentalService {

  RentalRepository rentalRepository;
  ComputerRepository computerRepository;
  RentalMapper rentalMapper;

  @Override
  public RentalDto getRental(Long id) {
    return rentalMapper.rentalToRentalDto(getRentalById(id));
  }

  @Override
  public List<RentalDto> getRentalsByDate(LocalDate rentDate) {
    return rentalRepository.findByRentDate(rentDate).stream().map(rentalMapper::rentalToRentalDto).toList();
  }

  @Override
  public RentalDto startRental(Long computerId, RentalDto rentalDto) {
    Computer computer = computerRepository.findById(computerId)
        .orElseThrow(() -> new ComputerNotFoundException(computerId));
    Rental rental = rentalMapper.rentalDtoToRental(rentalDto);
    rental.setComputer(computer);
    return rentalMapper.rentalToRentalDto(rentalRepository.save(rental));
  }

  @Override
  public RentalDto setRental(Long id, RentalDto rentalDto) {
    Rental rentalUpdated = rentalMapper.updateRentalFromDto(rentalDto, getRentalById(id));
    if (rentalUpdated.getEndTime() != null) {
      rentalUpdated.setTotal(calculateTotal(
          rentalUpdated.getStartTime(),
          rentalUpdated.getEndTime(),
          rentalUpdated.getPrice()
      ));
    }

    return rentalMapper.rentalToRentalDto(rentalRepository.save(rentalUpdated));
  }

  @Override
  public RentalDto endRental(Long id, LocalDateTime endTime) {
    Rental rental = getRentalById(id);
    if (endTime == null) {
      rental.setEndTime(LocalDateTime.now());
    } else if (endTime.isBefore(rental.getStartTime())) {
      throw new EndTimeBeforeStartTimeException(rental.getStartTime(), endTime);
    } else {
      rental.setEndTime(endTime);
    }

    rental.setTotal(calculateTotal(rental.getStartTime(), rental.getEndTime(), rental.getPrice()));
    return rentalMapper.rentalToRentalDto(rentalRepository.save(rental));
  }

  @Override
  public void removeRental(Long id) {
    rentalRepository.delete(getRentalById(id));
  }

  private Rental getRentalById(Long id) {

    return rentalRepository.findById(id)
        .orElseThrow(() -> new RentalNotFoundException(id));
  }

  private int calculateTotal(LocalDateTime initialTime, LocalDateTime endTime, int price) {
    long durationInMinutes = ChronoUnit.MINUTES.between(initialTime, endTime);
    int total = (int) Math.floor(1.0 * durationInMinutes / 60) * price;

    if (durationInMinutes % 60 >= 30) {
      return total + price;
    } else {
      return (int) (total + price * 0.5);
    }
  }

}
