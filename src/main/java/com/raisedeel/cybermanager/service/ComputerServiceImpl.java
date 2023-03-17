package com.raisedeel.cybermanager.service;

import com.raisedeel.cybermanager.dto.ComputerDto;
import com.raisedeel.cybermanager.dto.mapper.ComputerMapper;
import com.raisedeel.cybermanager.model.Computer;
import com.raisedeel.cybermanager.repository.ComputerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComputerServiceImpl implements ComputerService {
  ComputerRepository computerRepository;
  ComputerMapper computerMapper;

  @Override
  public ComputerDto getComputer(Long id) {
    Computer computer = computerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Could not find the requested computer"));
    return computerMapper.computerToComputerDto(computer);
  }

  @Override
  public List<ComputerDto> getComputers() {
    List<Computer> computers = (List<Computer>) computerRepository.findAll();
    return computers.stream().map(computerMapper::computerToComputerDto).toList();
  }

  @Override
  public ComputerDto addComputer(ComputerDto computerDto) {
    Computer computer = computerRepository.save(computerMapper.computerDtoToComputer(computerDto));
    return computerMapper.computerToComputerDto(computer);
  }

  @Override
  public ComputerDto setComputer(Long id, ComputerDto computerDto) {
    Computer computer = computerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Could not find the requested computer"));
    Computer updatedComputer = computerRepository.save(computerMapper.updateComputerFromDto(computerDto, computer));
    return computerMapper.computerToComputerDto(updatedComputer);
  }

  @Override
  public void removeComputer(Long id) {
    Computer computer = computerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Could not find the requested computer"));
    computerRepository.delete(computer);
  }
}
