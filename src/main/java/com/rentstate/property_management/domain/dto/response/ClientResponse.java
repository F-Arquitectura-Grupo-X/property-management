package com.rentstate.property_management.domain.dto.response;

import com.rentstate.property_management.domain.model.entities.Client;
import lombok.Data;

import java.util.Date;

@Data
public class ClientResponse {

    private Long id;
    private Long userId;
    private String clientName;
    private Date createdAt;

    public ClientResponse(Client client){
        id=client.getId();
        userId = client.getUserId();
        clientName = client.getClientName();
        createdAt = client.getCreatedAt();
    }

}
