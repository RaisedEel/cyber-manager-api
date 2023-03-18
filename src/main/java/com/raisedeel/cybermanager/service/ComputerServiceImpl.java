package com.raisedeel.cybermanager.service;

import com.raisedeel.cybermanager.dto.ComputerDto;
import com.raisedeel.cybermanager.dto.mapper.ComputerMapper;
import com.raisedeel.cybermanager.model.Computer;
import com.raisedeel.cybermanager.repository.ComputerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ComputerServiceImpl implements ComputerService {
  ComputerRepository computerRepository;
  ComputerMapper computerMapper;

  @Override
  public ComputerDto getComputer(Long id) {
    return computerMapper.computerToComputerDto(getComputerById(id));
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
    Computer updatedComputer = computerRepository.save(computerMapper.updateComputerFromDto(computerDto, getComputerById(id)));
    return computerMapper.computerToComputerDto(updatedComputer);
  }

  @Override
  public void removeComputer(Long id) {
    computerRepository.delete(getComputerById(id));
  }

  private Computer getComputerById(Long id) {
    return computerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Could not find the requested computer"));
  }
}
