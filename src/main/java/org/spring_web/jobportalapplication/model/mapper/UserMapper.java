package org.spring_web.jobportalapplication.model.mapper;

import org.spring_web.jobportalapplication.model.entity.User;
import org.spring_web.jobportalapplication.model.resource.UserResource;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResource toDTO(User user) {
        return new UserResource(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUserLocation(),
                user.getPhoneNumber(),
                user.getUserRole()
        );
    }


}
