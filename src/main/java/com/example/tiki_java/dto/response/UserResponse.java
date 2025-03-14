package com.example.tiki_java.dto.response;

import lombok.Data;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String email;
    String firstName;
    String lastName;
}
