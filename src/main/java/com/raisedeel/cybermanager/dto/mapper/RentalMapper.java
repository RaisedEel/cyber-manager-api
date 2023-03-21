package com.raisedeel.cybermanager.dto.mapper;

import com.raisedeel.cybermanager.dto.RentalDto;
import com.raisedeel.cybermanager.model.Rental;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring"
)
public interface RentalMapper {
  @Mapping(target = "computerDto", source = "computer")
  RentalDto rentalToRentalDto(Rental rental);

  @Mapping(target = "computer", source = "computerDto")
  Rental rentalDtoToRental(RentalDto rentalDto);

  @Mapping(target = "computer", source = "computerDto")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Rental updateRentalFromDto(RentalDto rentalDto, @MappingTarget Rental rental);
}
