package com.rentstate.property_management.domain.model.entities;

import com.rentstate.property_management.domain.dto.request.ReservationRequest;
import com.rentstate.property_management.domain.model.valueObjects.ReservationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;

    private String userName;

    @NotNull
    private Date reservationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @NotNull
    private Long ownerId;

    @ManyToOne
    @JoinColumn(name = "property_id")
    @NotNull
    private Property property;

    public Reservation(ReservationRequest reservationRequest, Property property) {
        this.userId = reservationRequest.getUserId();
        this.userName = reservationRequest.getUserName();
        ownerId = property.getOwnerId();
        reservationDate = new Date();
        status = ReservationStatus.PENDING;
        this.property = property;

    }

}
