package com.hrmsrevamp.repository;

import com.hrmsrevamp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * User Repository.
 */
@Repository
//@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndStatusNot(String email, String status);

    Optional<User> findById(Long userId);

    Boolean existsByEmail(String email);

    Optional<User> findByEmailAndStatusIn(String email, List<String> list);

  java.util.Collection<User> findByRoles_Name(String role);
}
