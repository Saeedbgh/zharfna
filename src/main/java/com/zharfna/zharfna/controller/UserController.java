package com.zharfna.zharfna.controller;

import com.zharfna.zharfna.dto.request.RegisterRequestDTO;
import com.zharfna.zharfna.entity.user.User;
import com.zharfna.zharfna.service.UserServiceImpl;
import jakarta.mail.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequestDTO registerRequestDTO){
        return userService.register(registerRequestDTO);
    }

/*    public Set<Address> findAllAddress(){
        return userService.findAllUserAddress();
    }*/
}
