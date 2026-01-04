package com.zharfna.zharfna.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String mobile;
    private String email;
    private List<String> roles;  // مثلا: ["ROLE_ADMIN"]
}
