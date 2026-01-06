package com.zharfna.zharfna.mapper;

import com.zharfna.zharfna.dto.request.RegisterRequestDTO;
import com.zharfna.zharfna.entity.user.User;
import com.zharfna.zharfna.enums.UserType;

import java.util.Set;

public class UserMapper {
    public static User toEntity(RegisterRequestDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setMobileNumber(dto.getMobile());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        user.setUserTypes(Set.of(UserType.PENDING));
        user.setEnabled(false);
        return user;
    }
}
