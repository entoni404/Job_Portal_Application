package al.job.portal.domain.mapper;

import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.model.resource.UserResource;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResource toDTO(User user) {
        return new UserResource(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPassword(),
            user.getUserLocation(),
            user.getPhoneNumber(),
            user.getUserRole()
        );
    }

    public User toEntity(UserResource resource) {
        if (resource == null) {
            return null;
        }

        return User.builder()
                .firstName(resource.firstName())
                .lastName(resource.lastName())
                .email(resource.email())
                .password(resource.password())
                .userLocation(resource.userLocation())
                .phoneNumber(resource.phoneNumber())
                .userRole(resource.role())
                .build();
    }
}
