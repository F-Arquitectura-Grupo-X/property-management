package com.rentstate.property_management.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Integer age;
    private String gender;
    private String description;
    private Boolean isPremium;
    private String photoUrl;
    private Double rating;
}
