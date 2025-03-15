package com.example.tiki_java.service;

import java.util.List;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.example.tiki_java.entity.User;
import org.springframework.stereotype.Service;
import com.example.tiki_java.mapper.UserMapper;
import com.example.tiki_java.exception.ErrorCode;
import com.example.tiki_java.exception.AppException;
import com.example.tiki_java.dto.response.UserResponse;
import com.example.tiki_java.repository.UserRepository;
import com.example.tiki_java.dto.request.UserCreateRequest;
import com.example.tiki_java.dto.request.UserUpdateRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.tiki_java.core.BaseService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements BaseService<User, UserResponse, UserCreateRequest, UserUpdateRequest> {

    UserMapper userMapper;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    /**
     * Kiểm tra email không được tồn tại.
     * Nếu tồn tại, ném lỗi USER_EXISTED.
     */
    public void ensureEmailNotExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
    }

    /**
     * Kiểm tra email có tồn tại không.
     * Nếu không tồn tại, ném lỗi USER_NOT_EXISTED.
     */
    public void ensureEmailExists(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
    }

    @Override
    public User getEntityById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse create(UserCreateRequest request) {
        ensureEmailNotExists(request.getEmail()); // Kiểm tra email chưa tồn tại

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getById(String id) {
        return userMapper.toUserResponse(getEntityById(id));
    }

    @Override
    public UserResponse update(String id, UserUpdateRequest request) {
        User user = getEntityById(id);
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void delete(String id) {
        User user = getEntityById(id);
        userRepository.delete(user);
    }
}
