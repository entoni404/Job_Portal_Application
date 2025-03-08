package org.spring_web.jobportalapplication.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register/{role}")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request, @PathVariable String role
    ){
        ResponseEntity<AuthenticationResponse> response = null;
        if(role.equals("ADMIN")){
            response = ResponseEntity.ok(authenticationService.registerAdmin(request));
        }else if(role.equals("EMPLOYER")){
            response = ResponseEntity.ok(authenticationService.registerEmployer(request));
        }else if(role.equals("JOB_SEEKER")){
            response = ResponseEntity.ok(authenticationService.registerJobSeeker(request));
        }else{
            throw new IllegalArgumentException("Invalid role");
        }
        return response;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}

