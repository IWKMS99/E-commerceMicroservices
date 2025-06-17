package iwkms.ecommerce.services.userservice.controller;

import iwkms.ecommerce.services.userservice.dto.RegisterUserDto;
import iwkms.ecommerce.services.userservice.entity.User;
import iwkms.ecommerce.services.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterUserDto registerDto) {
        User newUser = userService.registerUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User created successfully with ID: " + newUser.getId());
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok("Hello, " + authentication.getName());
    }
}