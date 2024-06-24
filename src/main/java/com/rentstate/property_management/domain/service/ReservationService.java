package com.rentstate.property_management.domain.service;

import com.rentstate.property_management.domain.dto.request.ReservationRequest;
import com.rentstate.property_management.domain.dto.response.ReservationResponse;

import java.util.List;

public interface ReservationService {

    Boolean addReservation(ReservationRequest reservationRequest);
    Boolean delete(Long reservationId);
    Boolean acceptReservation(Long reservationId);
    List<ReservationResponse> getReservationsByOwnerId(Long ownerId);

}
