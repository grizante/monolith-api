package br.com.grzsoftware.monolithapi.clients.mapper;

import br.com.grzsoftware.monolithapi.clients.dto.AddressDTO;
import br.com.grzsoftware.monolithapi.clients.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    Address addressDtoToAddress(AddressDTO addressDto);

    AddressDTO addressToAddressDto(Address address);
}
