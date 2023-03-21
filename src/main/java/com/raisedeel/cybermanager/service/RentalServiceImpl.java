package com.raisedeel.cybermanager.service;

import com.raisedeel.cybermanager.dto.RentalDto;
import com.raisedeel.cybermanager.dto.mapper.RentalMapper;
import com.raisedeel.cybermanager.model.Computer;
import com.raisedeel.cybermanager.model.Rental;
import com.raisedeel.cybermanager.repository.ComputerRepository;
import com.raisedeel.cybermanager.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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
        .orElseThrow(() -> new RuntimeException("Could not find the requested computer"));
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
  public RentalDto endRental(Long id, Date endTime) {
    Rental rental = getRentalById(id);
    if (endTime == null || endTime.before(rental.getStartTime())) {
      rental.setEndTime(new Date());
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
        .orElseThrow(() -> new RuntimeException("Could not find the requested rental"));
  }

  private int calculateTotal(Date initialTime, Date endTime, int price) {
    long durationInMinutes = (long) Math.floor(1.0 * (endTime.getTime() - initialTime.getTime()) / 60000);
    int total = (int) Math.floor(1.0 * durationInMinutes / 60) * price;

    if (durationInMinutes % 60 >= 30) {
      return total + price;
    } else {
      return (int) (total + price * 0.5);
    }
  }

}
