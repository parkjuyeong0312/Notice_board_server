package com.notice_board.notice_board.service;

import java.util.Map;
import java.util.Optional;



public interface UserService {
    
    // ===== CREATE =====
    /**
     * [Create] 회원가입
     * 새로운 사용자를 시스템에 등록합니다.
     */
    Map<String, Object> registerUser(String email, String password, String name, String nickname);
    
    // ===== READ =====
    /**
     * [Read] 이메일로 사용자 조회
     * 이메일을 통해 사용자 정보를 조회합니다.
     */
    Optional<Map<String, Object>> findByEmail(String email);
    
    /**
     * [Read] ID로 사용자 조회
     * 사용자 ID를 통해 사용자 정보를 조회합니다.
     */
    Optional<Map<String, Object>> findById(Long userId);
    
    // ===== UPDATE =====
    /**
     * [Update] 사용자 정보 수정
     * 사용자의 이름과 닉네임을 수정합니다.
     */
    Map<String, Object> updateUser(Long userId, String name, String nickname);
    
    /**
     * [Update] 비밀번호 변경
     * 사용자의 비밀번호를 변경합니다.
     * void 타입으로 반환하는 이유: 비밀번호 변경 성공 여부를 확인하기 위해 사용자에게 응답을 보내지 않음
     */
    // 비밀번호 변경 성공시 -> 반환할 필요 없음
    // 비밀번호 변경 실패시 -> 예외 처리 필요
    void changePassword(Long userId, String currentPassword, String newPassword);
    
    // ===== DELETE =====
    /**
     * [Delete] 사용자 탈퇴
     * 사용자 계정을 시스템에서 삭제합니다.
     */
    void deleteUser(Long userId);
}