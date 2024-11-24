package br.com.grzsoftware.monolithapi.service;

import br.com.grzsoftware.monolithapi.dto.*;
import br.com.grzsoftware.monolithapi.exception.ClientNotFoundException;
import br.com.grzsoftware.monolithapi.mapper.ClientMapper;
import br.com.grzsoftware.monolithapi.model.Client;
import br.com.grzsoftware.monolithapi.repository.AddressRepository;
import br.com.grzsoftware.monolithapi.repository.ClientRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public CreateClientResponseDto createClient(CreateClientDTO clientDto) {
        boolean isThereAClientWithEmail = clientRepository.findByEmail(clientDto.getEmail()).isPresent();
        if (isThereAClientWithEmail) {
            throw new IllegalArgumentException("Client with email " + clientDto.getEmail() + " already exists");
        }
        Client client = clientMapper.createClientDtoToClient(clientDto);
        if (client.getAddress() != null) {
            addressRepository.save(client.getAddress());
        }
        Client savedClient = clientRepository.save(client);
        return clientMapper.clientToCreateClientResponseDto(savedClient);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));
    }

    public Page<Client> getAllClients(FindAllClientsDTO queryDTO, Sort sort) {
        Page<Client> clients = clientRepository.findAll(PageRequest.of(queryDTO.getPage(), queryDTO.getSize(), sort));
        if (clients.isEmpty()) {
            return Page.empty();
        }
        return clients;
    }

    @Transactional
    public void updateClientById(Long id, UpdateClientDto clientDto) {
        clientDto.setId(id);
        clientRepository.findById(clientDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Client with id " + clientDto.getId() + " does not " + "exist"));
        clientRepository.save(clientMapper.updateClientDtoToClient(clientDto));
    }

    @Transactional
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
