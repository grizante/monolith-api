package br.com.grzsoftware.monolithapi.mapper;

import br.com.grzsoftware.monolithapi.dto.CreateClientDTO;
import br.com.grzsoftware.monolithapi.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "id", ignore = true)
    Client clientDtoToClient(CreateClientDTO createClientDTO);

    CreateClientDTO clientToClientDto(Client client);
}
