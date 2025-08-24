package com.notice_board.notice_board.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    // Task 1.3: 기본 컨트롤러 구조만 구현
    @PostMapping("/register")
    public Map<String, Object> register() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "회원가입 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> login() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "로그인 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @GetMapping("/me")
    public Map<String, Object> getCurrentUser() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "내 정보 조회 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }
}