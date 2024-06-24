package com.rentstate.property_management.domain.model.entities;

import com.rentstate.property_management.domain.dto.request.PropertyRequest;
import com.rentstate.property_management.domain.model.valueObjects.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@With
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String characteristics;

    @NotNull
    private String location;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull
    private Boolean available;

    @NotNull
    private Boolean posted;

    private String urlImg;

    @NotNull
    private Long ownerId;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Post post;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    public Property(PropertyRequest propertyRequest) {
        this.name = propertyRequest.getName();
        this.description = propertyRequest.getDescription();
        this.characteristics = propertyRequest.getCharacteristics();
        this.location = propertyRequest.getLocation();
        this.category = propertyRequest.getCategory();
        this.available = true;
        this.posted = false;
        this.urlImg = propertyRequest.getUrlImg();
        this.ownerId = propertyRequest.getOwnerId();
        this.reservations = new ArrayList<>();
    }

}
