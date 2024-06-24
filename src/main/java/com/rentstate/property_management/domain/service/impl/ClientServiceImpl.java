package com.rentstate.property_management.domain.service.impl;

import com.rentstate.property_management.domain.dto.response.ClientResponse;
import com.rentstate.property_management.domain.model.entities.Client;
import com.rentstate.property_management.domain.service.ClientService;
import com.rentstate.property_management.infrastructure.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<ClientResponse> getClientsByOwnerId(Long ownerId) {
        if(clientRepository.existsByOwnerId(ownerId)){
            List<Client> clients = clientRepository.findAllByOwnerId(ownerId);
            return clients.stream().map(ClientResponse::new).toList();
        }
        return null;
    }
}
