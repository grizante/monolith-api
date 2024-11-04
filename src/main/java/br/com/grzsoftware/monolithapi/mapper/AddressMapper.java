package br.com.grzsoftware.monolithapi.mapper;

import br.com.grzsoftware.monolithapi.dto.AddressDto;
import br.com.grzsoftware.monolithapi.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    Address addressDtoToAddress(AddressDto addressDto);

    AddressDto addressToAddressDto(Address address);
}
