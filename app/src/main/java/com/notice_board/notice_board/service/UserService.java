package com.notice_board.notice_board.service;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    
    /**
     * 회원가입
     */
    Map<String, Object> registerUser(String email, String password, String name, String nickname);
    
    /**
     * 이메일로 사용자 조회
     */
    Optional<Map<String, Object>> findByEmail(String email);
    
    /**
     * ID로 사용자 조회
     */
    Optional<Map<String, Object>> findById(Long userId);
    
    /**
     * 사용자 정보 수정
     */
    Map<String, Object> updateUser(Long userId, String name, String nickname);
    
    /**
     * 비밀번호 변경
     */
    void changePassword(Long userId, String currentPassword, String newPassword);
    
    /**
     * 사용자 탈퇴
     */
    void deleteUser(Long userId);
}