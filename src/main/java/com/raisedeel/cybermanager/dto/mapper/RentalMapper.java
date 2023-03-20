package com.raisedeel.cybermanager.dto.mapper;

import com.raisedeel.cybermanager.dto.RentalDto;
import com.raisedeel.cybermanager.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
    componentModel = "spring"
)
public interface RentalMapper {
  RentalDto rentalToRentalDto(Rental rental);

  Rental rentalDtoToRental(RentalDto rentalDto);

  Rental updateRentalFromDto(RentalDto rentalDto, @MappingTarget Rental rental);
}
