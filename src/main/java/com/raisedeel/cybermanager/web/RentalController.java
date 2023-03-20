package com.raisedeel.cybermanager.web;

import com.raisedeel.cybermanager.dto.RentalDto;
import com.raisedeel.cybermanager.service.RentalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/rental")
public class RentalController {

  RentalService rentalService;

  @GetMapping("/{id}")
  public ResponseEntity<RentalDto> getRentalHandler(@PathVariable Long id) {
    return new ResponseEntity<>(
        rentalService.getRental(id),
        HttpStatus.OK
    );
  }

  @GetMapping("/date/{rentDate}")
  public ResponseEntity<List<RentalDto>> getRentalsByRentDateHandler(@PathVariable LocalDate rentDate) {
    return new ResponseEntity<>(
        rentalService.getRentalsByDate(rentDate),
        HttpStatus.OK
    );
  }

  @PostMapping("/new")
  public ResponseEntity<RentalDto> startRentalHandler(@RequestBody @Valid RentalDto rentalDto) {
    return new ResponseEntity<>(
        rentalService.startRental(rentalDto),
        HttpStatus.CREATED
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<RentalDto> setRentalHandler(@PathVariable Long id, @RequestBody @Valid RentalDto rentalDto) {
    return new ResponseEntity<>(
        rentalService.setRental(id, rentalDto),
        HttpStatus.OK
    );
  }

  @PutMapping("finish/{id}")
  public ResponseEntity<RentalDto> endRentalHandler(@PathVariable Long id, @RequestParam(name = "time", required = false) Date endTime) {
    return new ResponseEntity<>(
        rentalService.endRental(id, endTime),
        HttpStatus.OK
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> removeRentalHandler(@PathVariable Long id) {
    rentalService.removeRental(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
