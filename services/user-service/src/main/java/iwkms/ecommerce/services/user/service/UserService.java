package iwkms.ecommerce.services.user.service;

import iwkms.ecommerce.libs.exception.UserAlreadyExistsException;
import iwkms.ecommerce.services.user.dto.RegisterUserDto;
import iwkms.ecommerce.services.user.entity.Role;
import iwkms.ecommerce.services.user.entity.User;
import iwkms.ecommerce.services.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(RegisterUserDto registerDto) {
        if (userRepository.findByEmail(registerDto.email()).isPresent()) {
            throw new UserAlreadyExistsException("User with email '" + registerDto.email() + "' already exists.");
        }

        User user = new User();
        user.setEmail(registerDto.email());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.setFirstName(registerDto.firstName());
        user.setLastName(registerDto.lastName());
        user.setRole(Role.USER);

        return userRepository.save(user);
    }
}