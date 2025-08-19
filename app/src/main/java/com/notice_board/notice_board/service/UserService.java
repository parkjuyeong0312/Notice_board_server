package com.notice_board.notice_board.service;

import java.util.Optional;

import com.notice_board.notice_board.entity.User;

public interface UserService {
    
    /**
     * 회원가입
     */
    User registerUser(String email, String password, String name, String nickname);
    
    /**
     * 이메일로 사용자 조회
     */
    Optional<User> findByEmail(String email);
    
    /**
     * ID로 사용자 조회
     */
    Optional<User> findById(Long userId);
    
    /**
     * 사용자 정보 수정
     */
    User updateUser(Long userId, String name, String nickname);
    
    /**
     * 비밀번호 변경
     */
    void changePassword(Long userId, String currentPassword, String newPassword);
    
    /**
     * 이메일 중복 확인
     */
    boolean isEmailExists(String email);
    
    /**
     * 닉네임 중복 확인
     */
    boolean isNicknameExists(String nickname);
    
    /**
     * 사용자 탈퇴 (soft delete)
     */
    void deleteUser(Long userId);
}