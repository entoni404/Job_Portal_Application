package al.job.portal.domain.controller;

import al.job.portal.domain.model.enums.UserRole;
import al.job.portal.domain.model.resource.UserResource;
import al.job.portal.domain.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUser(@PathVariable Long id) {
        UserResource user = userService.getUser(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<Page<UserResource>> getUsersByRole(
            @PathVariable(required = false) String role,
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        UserRole userRole = UserRole.valueOf(role.toUpperCase());
        Page<UserResource> userPage = userService.getUsersByRole(userRole, page, size);

        return ResponseEntity.ok(userPage);
    }

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody UserResource userResource) {
        UserResource createdUser = userService.createUser(userResource);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}