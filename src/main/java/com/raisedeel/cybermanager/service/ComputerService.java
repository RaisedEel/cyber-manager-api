package com.raisedeel.cybermanager.service;

import com.raisedeel.cybermanager.dto.ComputerDto;

import java.util.List;

public interface ComputerService {
  ComputerDto getComputer(Long id);

  List<ComputerDto> getComputers();

  ComputerDto addComputer(ComputerDto computerDto);

  ComputerDto setComputer(Long id, ComputerDto computerDto);

  void removeComputer(Long id);
}
