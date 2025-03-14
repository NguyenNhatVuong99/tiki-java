package com.example.tiki_java.controller;

import com.example.tiki_java.core.BaseRestController;
import lombok.AccessLevel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.example.tiki_java.core.ApiResponse;
import org.springframework.web.bind.annotation.*;
import com.example.tiki_java.service.UserService;
import com.example.tiki_java.dto.response.UserResponse;
import com.example.tiki_java.dto.request.UserCreateRequest;
import com.example.tiki_java.dto.request.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController implements BaseRestController<UserResponse,UserCreateRequest, UserUpdateRequest> {
    @Autowired
    UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getAll() {
        return ApiResponse.<List<UserResponse>>builder().result(userService.getAll()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getById(@PathVariable String id) {
        return ApiResponse.<UserResponse>builder().result(userService.getById(id)).build();
    }

    @PostMapping
    public ApiResponse<UserResponse> create(@RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.<UserResponse>builder().result(userService.create(request)).build();
    }

    @PatchMapping("/{id}")
    public ApiResponse<UserResponse> update(@PathVariable String id, @RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder().result(userService.update(id, request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable String id) {
        userService.delete(id);
        return ApiResponse.<Void>builder().message("Delete success").build();
    }
}
