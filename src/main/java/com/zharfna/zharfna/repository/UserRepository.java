package com.zharfna.zharfna.repository;

import com.zharfna.zharfna.entity.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByMobileNumber(String mobile);

    Optional<User> findByEmail(String email);

    boolean existsByMobileNumber(@NotBlank(message = " Mobile number could not be empty")
                                 @Pattern(regexp = "^09\\d{9}$",
                                         message = " Mobile format must be like: 09xxxxxxxxx ")
                                 String mobile);
}
