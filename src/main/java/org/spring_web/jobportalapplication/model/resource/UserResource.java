package org.spring_web.jobportalapplication.model.resource;

import org.spring_web.jobportalapplication.model.enums.UserRole;

public record UserResource(String firstName, String lastName, String email,
                           String userLocation, Long phoneNumber, UserRole role) {
}
