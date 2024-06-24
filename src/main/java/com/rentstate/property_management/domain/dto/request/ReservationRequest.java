package com.rentstate.property_management.domain.dto.request;

import lombok.Data;


@Data
public class ReservationRequest {

    private Long userId;
    private Long propertyId;
    private String userName;
}
