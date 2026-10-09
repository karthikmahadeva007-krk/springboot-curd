package com.krk.employee_management.Controller;

import com.krk.employee_management.Entity.Users;
import com.krk.employee_management.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody Users user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            users.getUsername(),
                            users.getPassword()
                    )
            );

            // If authentication is successful, set it in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "Login successful! Credentials are valid.";
        } catch (Exception e) {
            return "Invalid username or password!";
        }
    }
}
