package br.com.grzsoftware.monolithapi.service;

import br.com.grzsoftware.monolithapi.dto.CreateClientDTO;
import br.com.grzsoftware.monolithapi.mapper.ClientMapper;
import br.com.grzsoftware.monolithapi.model.Client;
import br.com.grzsoftware.monolithapi.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(CreateClientDTO clientDto) {
        Client client = ClientMapper.INSTANCE.clientDtoToClient(clientDto);
        return clientRepository.save(client);
    }
}
