package com.example.tiki_java.dto.response;

import lombok.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {
    String access_token;
    UserResponse user;
}
