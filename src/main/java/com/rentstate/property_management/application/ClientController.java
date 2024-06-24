package com.rentstate.property_management.application;


import com.rentstate.property_management.domain.dto.response.ClientResponse;
import com.rentstate.property_management.domain.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v17/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ClientResponse>> getClientsByOwnerId(@PathVariable Long ownerId){
        List<ClientResponse> clientResponseList = clientService.getClientsByOwnerId(ownerId);
        if(clientResponseList == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(clientResponseList, HttpStatus.OK);
    }

}
