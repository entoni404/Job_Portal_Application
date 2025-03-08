package org.spring_web.jobportalapplication.auth;


import lombok.RequiredArgsConstructor;
import org.spring_web.jobportalapplication.config.JwtService;
import org.spring_web.jobportalapplication.model.entity.User;
import org.spring_web.jobportalapplication.model.enums.UserRole;
import org.spring_web.jobportalapplication.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerJobSeeker(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userLocation(request.getUserLocation())
                .phoneNumber(request.getPhoneNumber())
                .userRole(UserRole.JOB_SEEKER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateSimpleToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse registerEmployer(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userLocation(request.getUserLocation())
                .phoneNumber(request.getPhoneNumber())
                .userRole(UserRole.EMPLOYER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateSimpleToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userLocation(request.getUserLocation())
                .phoneNumber(request.getPhoneNumber())
                .userRole(UserRole.ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateSimpleToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateSimpleToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
