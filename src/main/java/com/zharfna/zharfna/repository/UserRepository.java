package com.zharfna.zharfna.repository;

import com.zharfna.zharfna.entity.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByMobile(String mobile);

    Optional<User> findByEmail(String email);

    boolean existsByMobileNumber(@NotBlank(message = "شماره موبایل نمی‌تواند خالی باشد")
                                 @Pattern(regexp = "^09\\d{9}$",
                                         message = "شماره موبایل باید به فرمت 09xxxxxxxxx باشد")
                                 String mobile);
}
