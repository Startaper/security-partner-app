package com.ecommerce.securitypartnerapp.repositories;

import com.ecommerce.securitypartnerapp.entities.Role;
import com.ecommerce.securitypartnerapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    Set<Role> getAllRolesByUsername(String username);

}
