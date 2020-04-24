package com.springsecurity.jpa.repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.jpa.model.User;

@Repository
@ConditionalOnProperty(name = "authentication-type", havingValue = "jpa-authentication")
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserName(String username);
}
