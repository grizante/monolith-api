package br.com.grzsoftware.monolithapi.clients.service;

import br.com.grzsoftware.monolithapi.clients.dto.CreateClientDTO;
import br.com.grzsoftware.monolithapi.clients.dto.CreateClientResponseDto;
import br.com.grzsoftware.monolithapi.clients.dto.FindAllClientsDTO;
import br.com.grzsoftware.monolithapi.clients.dto.UpdateClientDto;
import br.com.grzsoftware.monolithapi.clients.exceptions.ClientAlreadyExists;
import br.com.grzsoftware.monolithapi.clients.exceptions.ClientNotFoundException;
import br.com.grzsoftware.monolithapi.clients.mapper.AddressMapper;
import br.com.grzsoftware.monolithapi.clients.mapper.ClientMapper;
import br.com.grzsoftware.monolithapi.clients.model.Address;
import br.com.grzsoftware.monolithapi.clients.model.Client;
import br.com.grzsoftware.monolithapi.clients.repository.AddressRepository;
import br.com.grzsoftware.monolithapi.clients.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ClientMapper clientMapper;
    private final AddressMapper addressMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository, ClientMapper clientMapper, AddressMapper addressMapper) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.clientMapper = clientMapper;
        this.addressMapper = addressMapper;
    }

    @Transactional
    public CreateClientResponseDto createClient(CreateClientDTO clientDto) {
        boolean isThereAClientWithEmail = clientRepository.findByEmail(clientDto.getEmail()).isPresent();
        if (isThereAClientWithEmail) {
            throw new ClientAlreadyExists("Client with email " + clientDto.getEmail() + " " + "already exists");
        }
        Client client = clientMapper.createClientDtoToClient(clientDto);
        Address address = client.getAddress();
        if (address != null) {
            String street = address.getStreet();
            String city = address.getCity();
            String state = address.getState();

            Optional<Address> optionalAddress = addressRepository.findAddressByCityAndStreetAndState(city, street, state);

            if (optionalAddress.isEmpty()) {
                addressRepository.save(client.getAddress());
            } else {
                client.setAddress(optionalAddress.get());
            }
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
        Client client = clientRepository.findById(clientDto.getId())
                .orElseThrow(() -> new ClientNotFoundException("Client with id " + clientDto.getId() + " does not " + "exist"));
        if (client.getAddress() != null) {
            Address address = addressRepository.save(addressMapper.addressDtoToAddress(clientDto.getAddress()));
            client.setAddress(address);
        }
        clientDto.setAddress(addressMapper.addressToAddressDto(client.getAddress()));
        clientRepository.save(clientMapper.updateClientDtoToClient(clientDto));
    }

    @Transactional
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
