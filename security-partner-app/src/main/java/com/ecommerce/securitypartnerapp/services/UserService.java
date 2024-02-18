package com.ecommerce.securitypartnerapp.services;

import com.ecommerce.securitypartnerapp.entities.Role;
import com.ecommerce.securitypartnerapp.entities.User;
import com.ecommerce.securitypartnerapp.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getByUsername(@NonNull String username) {
        return userRepository.findByUsername(username);
    }

    public Set<Role> getAllRolesByUsername(@NonNull String username) {
        return userRepository.getAllRolesByUsername(username);
    }

}
