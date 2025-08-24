package com.notice_board.notice_board.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    // Task 1.3: 기본 구조만 구현
    @Override
    public Map<String, Object> registerUser(String email, String password, String name, String nickname) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "사용자 등록 서비스 - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @Override
    public Optional<Map<String, Object>> findById(Long userId) {
        // Task 1.3: 기본 구조만 구현
        return Optional.empty();
    }

    @Override
    public Optional<Map<String, Object>> findByEmail(String email) {
        // Task 1.3: 기본 구조만 구현
        return Optional.empty();
    }

    @Override
    public Map<String, Object> updateUser(Long userId, String name, String nickname) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "사용자 수정 서비스 - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @Override
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        // Task 1.3: 기본 구조만 구현
    }

    @Override
    public void deleteUser(Long userId) {
        // Task 1.3: 기본 구조만 구현
    }
}