package com.krk.employee_management.Service;

///  Session management we use JWT token ------->  every login person having diffrent token ----> token expire time also thire

import com.krk.employee_management.Entity.Users;
import com.krk.employee_management.Repo.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword()) // Stored encrypted password
                .authorities(appUser.getRole())
                .build();
    }
}

