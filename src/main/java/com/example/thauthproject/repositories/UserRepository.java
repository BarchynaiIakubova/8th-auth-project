package com.example.thauthproject.repositories;

import com.example.thauthproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("""
            select u
            from User u
            where u.login = :login
            """)
    Optional<User> findByLogin(String login);
}
