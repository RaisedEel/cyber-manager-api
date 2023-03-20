package com.raisedeel.cybermanager.repository;

import com.raisedeel.cybermanager.model.Rental;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends CrudRepository<Rental, Long> {
  List<Rental> findByRentDate(LocalDate rentDate);
}
