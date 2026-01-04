package com.zharfna.zharfna.service;

import com.zharfna.zharfna.dto.request.LoginRequestDTO;
import com.zharfna.zharfna.dto.response.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO request);
}
