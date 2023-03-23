package com.raisedeel.cybermanager.web;

import com.raisedeel.cybermanager.dto.ComputerDto;
import com.raisedeel.cybermanager.service.ComputerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Tag(name = "Computer Controller", description = "Create, retrieve, update and delete computers records")
@RestController
@RequestMapping("/computer")
public class ComputerController {

  ComputerService computerService;

  @Operation(summary = "Retrieve a computer", description = "Retrieve a computer with the given unique identifier")
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ComputerDto> getComputerHandler(@PathVariable Long id) {
    return new ResponseEntity<>(
        computerService.getComputer(id),
        HttpStatus.OK
    );
  }

  @Operation(summary = "Retrieve computers", description = "Retrieve a list with all the computers")
  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ComputerDto>> getComputersHandler() {
    return new ResponseEntity<>(
        computerService.getComputers(),
        HttpStatus.OK
    );
  }

  @Operation(summary = "Create a new computer", description = "Create a new computer with the given payload")
  @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ComputerDto> addComputerHandler(@RequestBody @Valid ComputerDto computerDto) {
    return new ResponseEntity<>(
        computerService.addComputer(computerDto),
        HttpStatus.CREATED
    );
  }

  @Operation(summary = "Update a computer", description = "Update a computer with the given payload")
  @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ComputerDto> setComputerHandler(@PathVariable Long id, @RequestBody @Valid ComputerDto computerDto) {
    return new ResponseEntity<>(
        computerService.setComputer(id, computerDto),
        HttpStatus.OK
    );
  }

  @Operation(summary = "Delete a computer", description = "Delete the computer with the given unique identifier")
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HttpStatus> removeComputerHandler(@PathVariable Long id) {
    computerService.removeComputer(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
