package com.zharfna.zharfna.service;

import com.zharfna.zharfna.dto.request.RegisterRequestDTO;
import com.zharfna.zharfna.entity.user.User;
import com.zharfna.zharfna.enums.Role;
import com.zharfna.zharfna.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public User register(RegisterRequestDTO registerRequestDTO){
        User user = User.builder()
                .username(registerRequestDTO.username())
                .password(registerRequestDTO.password())
                .roles(Set.of(Role.USER))
                .build();
        return userRepository.save(user);
    }

}
