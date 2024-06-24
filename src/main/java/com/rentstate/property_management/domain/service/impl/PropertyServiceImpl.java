package com.rentstate.property_management.domain.service.impl;

import com.rentstate.property_management.domain.dto.request.PropertyRequest;
import com.rentstate.property_management.domain.dto.response.PropertyResponse;
import com.rentstate.property_management.domain.model.entities.Property;
import com.rentstate.property_management.domain.service.PropertyService;
import com.rentstate.property_management.infrastructure.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;

    @Override
    public PropertyResponse addProperty(PropertyRequest propertyRequest) {
        Property property = new Property(propertyRequest);
        propertyRepository.save(property);
        return new PropertyResponse(property);
    }

    @Override
    public PropertyResponse updateProperty(PropertyRequest propertyRequest, Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElse(null);
        if (property != null) {
            property.setName(propertyRequest.getName());
            property.setDescription(propertyRequest.getDescription());
            property.setCharacteristics(propertyRequest.getCharacteristics());
            property.setLocation(propertyRequest.getLocation());
            property.setCategory(propertyRequest.getCategory());
            property.setUrlImg(property.getUrlImg());

            propertyRepository.save(property);
            return new PropertyResponse(property);
        }
        return null;
    }

    @Override
    public Boolean deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
        return true;
    }

    @Override
    public PropertyResponse getPropertyById(Long id) {
        return propertyRepository.findById(id).map(PropertyResponse::new).orElse(null);
    }

    @Override
    public List<PropertyResponse> getPropertiesByAuthorId(Long ownerId) {

        if(propertyRepository.existsById(ownerId)) {
            List<Property>propertyList = propertyRepository.findAllByOwnerId(ownerId);

            return propertyList.stream()
                    .map(PropertyResponse::new).toList();
        }
        return null;
    }
}
