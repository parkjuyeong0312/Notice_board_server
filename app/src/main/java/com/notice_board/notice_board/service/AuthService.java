package com.notice_board.notice_board.service;

import java.util.Map;

public interface AuthService {
    
    /**
     * 사용자 회원가입
     */
    Map<String, Object> register(String email, String password, String name, String nickname);
    
    /**
     * 사용자 로그인
     */
    Map<String, Object> login(String email, String password);
    
    /**
     * 토큰 갱신
     */
    Map<String, Object> refreshToken(String refreshToken);
    
    /**
     * 현재 사용자 정보 조회
     */
    Map<String, Object> getCurrentUser(Long userId);
    
    /**
     * 비밀번호 변경
     */
    void changePassword(Long userId, String currentPassword, String newPassword);
}