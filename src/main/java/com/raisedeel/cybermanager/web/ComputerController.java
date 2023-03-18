package com.raisedeel.cybermanager.web;

import com.raisedeel.cybermanager.dto.ComputerDto;
import com.raisedeel.cybermanager.service.ComputerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/computer")
public class ComputerController {

  ComputerService computerService;

  @GetMapping("/{id}")
  public ResponseEntity<ComputerDto> getComputerHandler(@PathVariable Long id) {
    return new ResponseEntity<>(
        computerService.getComputer(id),
        HttpStatus.OK
    );
  }

  @GetMapping("/all")
  public ResponseEntity<List<ComputerDto>> getComputersHandler() {
    return new ResponseEntity<>(
        computerService.getComputers(),
        HttpStatus.OK
    );
  }

  @PostMapping("/new")
  public ResponseEntity<ComputerDto> addComputerHandler(@RequestBody @Valid ComputerDto computerDto) {
    return new ResponseEntity<>(
        computerService.addComputer(computerDto),
        HttpStatus.CREATED
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<ComputerDto> setComputerHandler(@PathVariable Long id, @RequestBody @Valid ComputerDto computerDto) {
    return new ResponseEntity<>(
        computerService.setComputer(id, computerDto),
        HttpStatus.OK
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> removeComputerHandler(@PathVariable Long id) {
    computerService.removeComputer(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
