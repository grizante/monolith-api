package br.com.grzsoftware.monolithapi.mapper;

import br.com.grzsoftware.monolithapi.dto.CreateClientDTO;
import br.com.grzsoftware.monolithapi.dto.CreateClientResponseDto;
import br.com.grzsoftware.monolithapi.dto.UpdateClientDto;
import br.com.grzsoftware.monolithapi.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    Client createClientDtoToClient(CreateClientDTO createClientDTO);

    Client updateClientDtoToClient(UpdateClientDto updateClientDTO);

    CreateClientResponseDto clientToCreateClientResponseDto(Client client);

    CreateClientDTO clientToCreateClientDto(Client client);
}
