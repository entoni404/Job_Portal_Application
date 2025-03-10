package al.job.portal.auth.controller;

import al.job.portal.auth.model.AuthenticationRequest;
import al.job.portal.auth.model.AuthenticationResponse;
import al.job.portal.auth.service.AuthenticationService;
import al.job.portal.auth.model.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerAdmin(request));
    }

    @PostMapping("/register/employer")
    public ResponseEntity<AuthenticationResponse> registerEmployer(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerEmployer(request));
    }

    @PostMapping("/register/job-seeker")
    public ResponseEntity<AuthenticationResponse> registerJobSeeker(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerJobSeeker(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}

