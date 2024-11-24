package br.com.grzsoftware.monolithapi.service;

import br.com.grzsoftware.monolithapi.dto.CreateClientDTO;
import br.com.grzsoftware.monolithapi.dto.PaginationQueryDTO;
import br.com.grzsoftware.monolithapi.mapper.ClientMapper;
import br.com.grzsoftware.monolithapi.model.Client;
import br.com.grzsoftware.monolithapi.repository.AddressRepository;
import br.com.grzsoftware.monolithapi.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.clientMapper = clientMapper;
    }

    public Client createClient(CreateClientDTO clientDto) {
        Client client = clientMapper.clientDtoToClient(clientDto);
        if (client.getAddress() != null) {
            addressRepository.save(client.getAddress());
        }
        return clientRepository.save(client);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
    }

    public Page<Client> getAllClients(PaginationQueryDTO queryDTO, Sort sort) {
        Page<Client> clients = clientRepository.findAll(PageRequest.of(queryDTO.getPage(), queryDTO.getSize(), sort));
        if(clients.getTotalElements() < 1) {
            throw new EntityNotFoundException("No client found");
        }
        return clients;
    }
}
