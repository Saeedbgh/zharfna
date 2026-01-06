package com.zharfna.zharfna.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

    @NotBlank(message = "نام نمی‌تواند خالی باشد")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "نام خانوادگی نمی‌تواند خالی باشد")
    @Size(max = 50)
    private String lastName;

    @NotBlank(message = "شماره موبایل نمی‌تواند خالی باشد")
    @Pattern(regexp = "^09\\d{9}$", message = "شماره موبایل باید به فرمت 09xxxxxxxxx باشد")
    private String mobile;

    @Email(message = "ایمیل نامعتبر است")
    private String email;

    @NotBlank(message = "رمز عبور نمی‌تواند خالی باشد")
    @Size(min = 6, message = "رمز عبور باید حداقل 6 کاراکتر باشد")
    private String password;
}
