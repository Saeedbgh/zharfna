package com.zharfna.zharfna.security.service;

import com.zharfna.zharfna.entity.User;
import com.zharfna.zharfna.repository.UserRepository;
import com.zharfna.zharfna.security.principal.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        log.debug("Loading user by mobile: {}", mobile);

        User user = userRepository.findByMobile(mobile)
                .orElseThrow(() -> {
                    log.error("User not found with mobile: {}", mobile);
                    return new UsernameNotFoundException("کاربر با شماره موبایل " + mobile + " یافت نشد");
                });

        if (!user.isActive()) {
            log.warn("Inactive user tried to login with mobile: {}", mobile);
            throw new UsernameNotFoundException("حساب کاربری غیرفعال است");
        }

        log.debug("User loaded successfully: {}", mobile);
        return UserPrincipal.from(user);
    }
}
