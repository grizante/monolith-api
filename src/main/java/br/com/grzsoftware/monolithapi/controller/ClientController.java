package br.com.grzsoftware.monolithapi.controller;

import br.com.grzsoftware.monolithapi.dto.CreateClientDTO;
import br.com.grzsoftware.monolithapi.dto.CreateClientResponseDto;
import br.com.grzsoftware.monolithapi.dto.PaginationQueryDTO;
import br.com.grzsoftware.monolithapi.model.Client;
import br.com.grzsoftware.monolithapi.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public CreateClientResponseDto create(@Valid @RequestBody CreateClientDTO CreateClientDTO) {
        return clientService.createClient(CreateClientDTO);
    }

    @GetMapping
    public Page<Client> getClients(@Valid PaginationQueryDTO paginationQueryDTO) {
        Sort sort = paginationQueryDTO.getSort() != null ? Sort.by(paginationQueryDTO.getSort()) : Sort.unsorted();

        return clientService.getAllClients(paginationQueryDTO, sort);
    }
}
