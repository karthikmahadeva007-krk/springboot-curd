package com.krk.employee_management.Service;



import com.krk.employee_management.Entity.Users;
import com.krk.employee_management.Repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(Users user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username is already taken!";
        }

        // Encrypt the plain text password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER"); // Assign default user authorization role
        userRepository.save(user);

        return "User registered successfully!";
    }
}
