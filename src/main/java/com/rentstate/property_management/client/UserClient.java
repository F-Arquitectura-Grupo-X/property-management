package com.rentstate.property_management.client;

import com.rentstate.property_management.domain.dto.response.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc-userManagement")
public interface UserClient {

    @GetMapping("/{userId}")
    UserDTO getUser(@PathVariable Long userId);

}
