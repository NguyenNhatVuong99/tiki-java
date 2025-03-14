package com.example.tiki_java.repository;

import com.example.tiki_java.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
