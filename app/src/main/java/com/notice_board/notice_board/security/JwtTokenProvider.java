package com.notice_board.notice_board.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // Task 1.3: 기본 구조만 구현
    public String generateAccessToken(String email, Long userId) {
        // 기본 구조만 구현
        return "dummy_access_token_" + email + "_" + userId;
    }

    public String generateRefreshToken(String email, Long userId) {
        // 기본 구조만 구현
        return "dummy_refresh_token_" + email + "_" + userId;
    }

    public boolean validateToken(String token) {
        // 기본 구조만 구현
        return token != null && !token.isEmpty();
    }

    public String getEmailFromToken(String token) {
        // 기본 구조만 구현
        return "dummy@example.com";
    }

    public Long getUserIdFromToken(String token) {
        // 기본 구조만 구현
        return 1L;
    }

    public boolean isRefreshToken(String token) {
        // 기본 구조만 구현
        return token != null && token.startsWith("dummy_refresh_token");
    }
}