package al.job.portal.auth.service;


import al.job.portal.auth.model.AuthenticationRequest;
import al.job.portal.auth.model.AuthenticationResponse;
import al.job.portal.auth.model.RegisterRequest;
import al.job.portal.domain.model.enums.UserRole;
import al.job.portal.domain.model.resource.UserResource;
import al.job.portal.domain.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserService userService,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        String password = passwordEncoder.encode(request.getPassword());
        UserResource user = new UserResource(request.getFirstName(), request.getLastName(), request.getEmail(),
                password, request.getUserLocation(), request.getPhoneNumber(), UserRole.ADMIN);

        userService.createUser(user);

        String jwtToken = jwtService.generateSimpleToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse registerEmployer(RegisterRequest request) {
        String password = passwordEncoder.encode(request.getPassword());
        UserResource user = new UserResource(request.getFirstName(), request.getLastName(), request.getEmail(),
                password, request.getUserLocation(), request.getPhoneNumber(), UserRole.EMPLOYER);

        userService.createUser(user);

        String jwtToken = jwtService.generateSimpleToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse registerJobSeeker(RegisterRequest request) {
        String password = passwordEncoder.encode(request.getPassword());
        UserResource user = new UserResource(request.getFirstName(), request.getLastName(), request.getEmail(),
                password, request.getUserLocation(), request.getPhoneNumber(), UserRole.JOB_SEEKER);

        userService.createUser(user);

        String jwtToken = jwtService.generateSimpleToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(token);

        UserResource user = userService.getUserByEmail(request.getEmail());
        String jwtToken = jwtService.generateSimpleToken(user);

        return new AuthenticationResponse(jwtToken);
    }
}
