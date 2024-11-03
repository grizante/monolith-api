package br.com.grzsoftware.monolithapi.controller;

import br.com.grzsoftware.monolithapi.dto.CreateClientDTO;
import br.com.grzsoftware.monolithapi.model.Client;
import br.com.grzsoftware.monolithapi.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public Client create(@Valid @RequestBody CreateClientDTO CreateClientDTO) {
        return clientService.createClient(CreateClientDTO);
    }
}
