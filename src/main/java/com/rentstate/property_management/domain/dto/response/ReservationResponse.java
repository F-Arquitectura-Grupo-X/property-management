package com.rentstate.property_management.domain.dto.response;

import com.rentstate.property_management.domain.model.entities.Reservation;
import com.rentstate.property_management.domain.model.valueObjects.ReservationStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationResponse {

    private Long id;
    private Long userId;
    private String userName;
    private Date reservationDate;
    private ReservationStatus status;
    private PropertyResponse property ;

    public ReservationResponse(Reservation reservation){
        this.id = reservation.getId();
        this.userId = reservation.getUserId();
        this.userName = reservation.getUserName();
        this.reservationDate = reservation.getReservationDate();
        this.status = reservation.getStatus();
        this.property = new PropertyResponse(reservation.getProperty());
    }
}
