package com.notice_board.notice_board.service;

import java.util.Map;

public interface AuthService {
    
    // ===== CREATE =====
    /**
     * [Create] 사용자 회원가입
     * 새로운 사용자를 시스템에 등록합니다.
     */
    Map<String, Object> register(String email, String password, String name, String nickname);
    
    // ===== READ =====
    /**
     * [Read] 사용자 로그인
     * 사용자 인증 및 로그인을 처리합니다.
     */
    Map<String, Object> login(String email, String password);
    
    /**
     * [Read] 토큰 갱신
     * 만료된 토큰을 새로운 토큰으로 갱신합니다.
     */
    Map<String, Object> refreshToken(String refreshToken);
    
    /**
     * [Read] 현재 사용자 정보 조회
     * 현재 로그인된 사용자의 정보를 조회합니다.
     */
    Map<String, Object> getCurrentUser(Long userId);
    
    // ===== UPDATE =====
    /**
     * [Update] 비밀번호 변경
     * 사용자의 비밀번호를 변경합니다.
     */
    void changePassword(Long userId, String currentPassword, String newPassword);
}