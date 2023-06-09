package com.raisedeel.cybermanager.dto.mapper;

import com.raisedeel.cybermanager.dto.ComputerDto;
import com.raisedeel.cybermanager.model.Computer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring"
)
public interface ComputerMapper {
  ComputerDto computerToComputerDto(Computer computer);

  Computer computerDtoToComputer(ComputerDto computerDto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Computer updateComputerFromDto(ComputerDto computerDto, @MappingTarget Computer computer);
}
