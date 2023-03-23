package com.raisedeel.cybermanager.web;

import com.raisedeel.cybermanager.dto.RentalDto;
import com.raisedeel.cybermanager.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Tag(name = "Rental Controller", description = "Start and finish rentals. Retrieve, update and remove past rentals")
@RestController
@RequestMapping("/rental")
public class RentalController {

  RentalService rentalService;

  @Operation(summary = "Retrieve a rental", description = "Retrieve a rental with the given unique identifier")
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RentalDto> getRentalHandler(@PathVariable Long id) {
    return new ResponseEntity<>(
        rentalService.getRental(id),
        HttpStatus.OK
    );
  }

  @Operation(summary = "Retrieve rentals of a given day", description = "Retrieve a list of rentals based of a given day")
  @GetMapping(value = "/date/{rentDate}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<RentalDto>> getRentalsByRentDateHandler(@PathVariable LocalDate rentDate) {
    return new ResponseEntity<>(
        rentalService.getRentalsByDate(rentDate),
        HttpStatus.OK
    );
  }

  @Operation(summary = "Create new rental", description = "Create a new rental from the provided ID of a computer and the payload")
  @PostMapping(value = "/new/{computerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RentalDto> startRentalHandler(@PathVariable Long computerId, @RequestBody @Valid RentalDto rentalDto) {
    return new ResponseEntity<>(
        rentalService.startRental(computerId, rentalDto),
        HttpStatus.CREATED
    );
  }

  @Operation(summary = "Update a rental", description = "Update a rental with the given payload")
  @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RentalDto> setRentalHandler(@PathVariable Long id, @RequestBody @Valid RentalDto rentalDto) {
    return new ResponseEntity<>(
        rentalService.setRental(id, rentalDto),
        HttpStatus.OK
    );
  }

  @Operation(summary = "Finish a rental", description = "Update the end time of a rental and calculate the total")
  @PutMapping(value = "finish/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RentalDto> endRentalHandler(@PathVariable Long id, @RequestParam(name = "time", required = false) LocalDateTime endTime) {
    return new ResponseEntity<>(
        rentalService.endRental(id, endTime),
        HttpStatus.OK
    );
  }

  @Operation(summary = "Delete a rental", description = "Delete the rental with the given unique identifier")
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HttpStatus> removeRentalHandler(@PathVariable Long id) {
    rentalService.removeRental(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
