package br.com.grzsoftware.monolithapi.clients.mapper;

import br.com.grzsoftware.monolithapi.clients.dto.CreateClientDTO;
import br.com.grzsoftware.monolithapi.clients.dto.CreateClientResponseDto;
import br.com.grzsoftware.monolithapi.clients.dto.UpdateClientDto;
import br.com.grzsoftware.monolithapi.clients.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    Client createClientDtoToClient(CreateClientDTO createClientDTO);

    Client updateClientDtoToClient(UpdateClientDto updateClientDTO);

    CreateClientResponseDto clientToCreateClientResponseDto(Client client);

    CreateClientDTO clientToCreateClientDto(Client client);
}
