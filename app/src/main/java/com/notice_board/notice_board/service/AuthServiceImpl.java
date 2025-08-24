package com.notice_board.notice_board.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    // Task 1.3: 기본 구조만 구현
    @Override
    public Map<String, Object> register(String email, String password, String name, String nickname) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "회원가입 서비스 - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @Override
    public Map<String, Object> login(String email, String password) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "로그인 서비스 - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @Override
    public Map<String, Object> getCurrentUser(Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "사용자 정보 조회 서비스 - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @Override
    public Map<String, Object> refreshToken(String refreshToken) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "토큰 갱신 서비스 - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @Override
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        // Task 1.3: 기본 구조만 구현
    }
}