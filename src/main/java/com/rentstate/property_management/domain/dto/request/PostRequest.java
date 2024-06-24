package com.rentstate.property_management.domain.dto.request;

import com.rentstate.property_management.domain.model.entities.Property;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class PostRequest {

    private String title;
    private Double price;
    private Long propertyId;

}
