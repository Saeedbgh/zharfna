package com.zharfna.zharfna.repository;

import com.zharfna.zharfna.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByCellPhoneNumber(String cellPhoneNumber);

    boolean existsByEmail(String email);
    boolean existsByCellPhoneNumber(String cellPhoneNumber);
}