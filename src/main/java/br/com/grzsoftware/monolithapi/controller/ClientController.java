package br.com.grzsoftware.monolithapi.controller;

import br.com.grzsoftware.monolithapi.dto.*;
import br.com.grzsoftware.monolithapi.model.Client;
import br.com.grzsoftware.monolithapi.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    private FindAllClientsDTO nextPageDTO(FindAllClientsDTO current) {
        return new FindAllClientsDTO(current.getPage() + 1, current.getSize(), current.getSort());
    }

    private FindAllClientsDTO previousPageDTO(FindAllClientsDTO current) {
        return new FindAllClientsDTO(current.getPage() - 1, current.getSize(), current.getSort());
    }

    @PostMapping
    public EntityModel<CreateClientResponseDto> createClient(@Valid @RequestBody CreateClientDTO CreateClientDTO) {
        CreateClientResponseDto client = clientService.createClient(CreateClientDTO);

        EntityModel<CreateClientResponseDto> clientEntityModel = EntityModel.of(client);

        clientEntityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).getClientById(client.getId())).withSelfRel());

        return clientEntityModel;
    }

    @GetMapping("{id}")
    public EntityModel<Client> getClientById(@PathVariable("id") Long id) {
        Client client = clientService.getClientById(id);

        EntityModel<Client> clientEntityModel = EntityModel.of(client);

        clientEntityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).getClientById(id)).withSelfRel());

        return clientEntityModel;
    }

    @GetMapping
    public PagedModel<EntityModel<Client>> getClients(@Valid FindAllClientsDTO findAllClientsDTO) {
        Sort sort = findAllClientsDTO.getSort() != null ? Sort.by(findAllClientsDTO.getSort()) : Sort.unsorted();

        Page<Client> clients = clientService.getAllClients(findAllClientsDTO, sort);

        PagedModel<EntityModel<Client>> clientPagedModel = PagedModel.of(clients.stream().map(client -> {
                    EntityModel<Client> clientEntityModel = EntityModel.of(client);
                    clientEntityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).getClientById(client.getId())).withSelfRel());
                    clientEntityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).getClients(findAllClientsDTO)).withRel("clients"));
                    return clientEntityModel;
                }).collect(Collectors.toList()),
                new PagedModel.PageMetadata(clients.getSize(), clients.getNumber(), clients.getTotalElements())
        );

        clientPagedModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass())
                .getClients(findAllClientsDTO)).withSelfRel());
        if (clients.hasNext()) {
            clientPagedModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass())
                    .getClients(nextPageDTO(findAllClientsDTO))).withRel("next"));
        }
        if (clients.hasPrevious()) {
            clientPagedModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass())
                    .getClients(previousPageDTO(findAllClientsDTO))).withRel("previous"));
        }

        return clientPagedModel;
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateClientById(@PathVariable("id") Long id, @Valid @RequestBody UpdateClientDto updateClientDto) {
        clientService.updateClientById(id, updateClientDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }
}
