package org.example.myrefrigerator.user.repository;

import org.example.myrefrigerator.user.entity.Provider;
import org.example.myrefrigerator.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByProviderAndProviderId(
            Provider provider,
            String providerId
    );

}