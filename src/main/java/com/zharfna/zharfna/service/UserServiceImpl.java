package com.zharfna.zharfna.service;

import com.zharfna.zharfna.dto.request.RegisterRequestDTO;
import com.zharfna.zharfna.dto.response.RegisterResponseDTO;
import com.zharfna.zharfna.entity.user.User;
import com.zharfna.zharfna.mapper.UserMapper;
import com.zharfna.zharfna.repository.UserRepository;
import com.zharfna.zharfna.service.base.crud.CrudServiceImpl;
import jakarta.validation.Validator;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        extends CrudServiceImpl<User, Long, UserRepository>
         {

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository repository,
            Validator validator,
            PasswordEncoder passwordEncoder
    ) {
        super(repository, validator);
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {

        if (repository.existsByMobileNumber(registerRequestDTO.getMobile())) {
            throw new IllegalStateException("This phone number has already registered");
        }

        User user = UserMapper.toEntity(registerRequestDTO);

        user.setPassword(
                passwordEncoder.encode(registerRequestDTO.getPassword())
        );


        User savedUser = repository.save(user);


        return new RegisterResponseDTO(
                savedUser.getId(),
                savedUser.getMobileNumber(),
                "User registered successfully"
        );
    }


    public UserDetails loadUserByUsername(@NonNull String username)
            throws UsernameNotFoundException {

        return (UserDetails) repository.findByMobileNumber(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with mobile: " + username
                        )
                );
    }
}
