package com.rentstate.property_management.domain.service;

import com.rentstate.property_management.domain.dto.response.ClientResponse;

import java.util.List;

public interface ClientService {
    List<ClientResponse> getClientsByOwnerId(Long ownerId);
}
