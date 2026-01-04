package com.zharfna.zharfna.service;

import com.zharfna.zharfna.dto.request.LoginRequestDTO;
import com.zharfna.zharfna.dto.response.LoginResponseDTO;
import com.zharfna.zharfna.entity.User;
import com.zharfna.zharfna.jwt.JwtTokenProvider;
import com.zharfna.zharfna.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginRequestDTO request) {
        log.debug("Login attempt for mobile: {}", request.getMobile());

        User user = userRepository.findByMobile(request.getMobile())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "کاربری با شماره موبایل " + request.getMobile() + " یافت نشد"
                ));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("Invalid password attempt for mobile: {}", request.getMobile());
            throw new BadCredentialsException("رمز عبور اشتباه است");
        }

        if (!user.isEnabled()) {
            log.warn("Disabled user login attempt: {}", request.getMobile());
            throw new BadCredentialsException("حساب کاربری غیرفعال است");
        }

        // تبدیل Role به لیست String
        List<String> roles = List.of("ROLE_" + user.getRole().name());

        String token = jwtTokenProvider.generateToken(user.getMobile(), roles);

        log.info("User logged in successfully: {} with role: {}",
                request.getMobile(), user.getRole());

        return new LoginResponseDTO(
                token,
                user.getMobile(),
                user.getEmail(),
                roles
        );
    }
}
