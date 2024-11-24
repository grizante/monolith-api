package br.com.grzsoftware.monolithapi.controller;

import br.com.grzsoftware.monolithapi.dto.CreateClientDTO;
import br.com.grzsoftware.monolithapi.dto.PaginationQueryDTO;
import br.com.grzsoftware.monolithapi.model.Client;
import br.com.grzsoftware.monolithapi.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    private PaginationQueryDTO nextPageDTO(PaginationQueryDTO current) {
        return new PaginationQueryDTO(current.getPage() + 1, current.getSize(), current.getSort());
    }

    private PaginationQueryDTO previousPageDTO(PaginationQueryDTO current) {
        return new PaginationQueryDTO(current.getPage() - 1, current.getSize(), current.getSort());
    }

    @PostMapping
    public EntityModel<Client> createClient(@Valid @RequestBody CreateClientDTO CreateClientDTO) {
        Client client = clientService.createClient(CreateClientDTO);

        EntityModel<Client> clientEntityModel = EntityModel.of(client);

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
    public PagedModel<EntityModel<Client>> getClients(@Valid PaginationQueryDTO paginationQueryDTO) {
        Sort sort = paginationQueryDTO.getSort() != null ? Sort.by(paginationQueryDTO.getSort()) : Sort.unsorted();

        Page<Client> clients = clientService.getAllClients(paginationQueryDTO, sort);

        PagedModel<EntityModel<Client>> clientPagedModel = PagedModel.of(clients.stream().map(client -> {
                    EntityModel<Client> clientEntityModel = EntityModel.of(client);
                    clientEntityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).getClientById(client.getId())).withSelfRel());
                    clientEntityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).getClients(paginationQueryDTO)).withRel("clients"));
                    return clientEntityModel;
                }).collect(Collectors.toList()),
                new PagedModel.PageMetadata(clients.getSize(), clients.getNumber(), clients.getTotalElements())
        );

        clientPagedModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass())
                .getClients(paginationQueryDTO)).withSelfRel());
        if(clients.hasContent()) {
            clientPagedModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass())
                .getClients(nextPageDTO(paginationQueryDTO))).withRel("next"));
        }
        if(clients.hasPrevious()) {
            clientPagedModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass())
                .getClients(previousPageDTO(paginationQueryDTO))).withRel("previous"));
        }

        return clientPagedModel;
    }
}
