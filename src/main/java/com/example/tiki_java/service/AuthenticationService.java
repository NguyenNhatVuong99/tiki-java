package com.example.tiki_java.service;

import com.example.tiki_java.dto.request.RegisterRequest;
import com.example.tiki_java.dto.request.UserCreateRequest;
import com.example.tiki_java.dto.response.RegisterResponse;
import com.example.tiki_java.dto.response.UserResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.experimental.FieldDefaults;
import com.example.tiki_java.utils.JWTUtil;
import org.springframework.stereotype.Service;
import com.example.tiki_java.mapper.UserMapper;
import com.example.tiki_java.entity.JWTProperty;
import com.example.tiki_java.exception.ErrorCode;
import com.example.tiki_java.exception.AppException;
import com.example.tiki_java.dto.request.LoginRequest;
import com.example.tiki_java.repository.UserRepository;
import com.example.tiki_java.dto.response.LoginResponse;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Service
@AllArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    JWTUtil jwtUtil;
    UserMapper userMapper;
    JWTProperty jwtProperty;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserService userService;

    public LoginResponse login(LoginRequest request) {
        log.info("ok");
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        String token = jwtUtil.generateToken(user);
        return LoginResponse.builder().user(userMapper.toUserResponse(user)).access_token(token).build();
    }

    public RegisterResponse register(RegisterRequest request) {
        UserCreateRequest userCreateRequest = userMapper.toUserCreateRequest(request);
        UserResponse userResponse = userService.create(userCreateRequest);
        return RegisterResponse.builder().user(userResponse).build();
    }
}
