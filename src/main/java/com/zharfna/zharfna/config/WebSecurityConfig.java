package com.zharfna.zharfna.config;

import com.zharfna.zharfna.enums.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collections;

@Configuration
public class WebSecurityConfig {

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("admin")
                .password("admin123")
                .roles(String.valueOf(Collections.singleton(UserRole.ARTIST)))
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
