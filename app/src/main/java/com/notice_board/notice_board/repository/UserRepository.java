package com.notice_board.notice_board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.notice_board.notice_board.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * 이메일로 사용자 조회
     * 로그인 시 사용
     */
    Optional<User> findByEmail(String email);
    
    /**
     * 이메일 중복 확인
     * 회원가입 시 사용
     */
    boolean existsByEmail(String email);
    
    /**
     * 닉네임 중복 확인
     * 회원가입 시 사용
     */
    boolean existsByNickname(String nickname);
    
    /**
     * 닉네임으로 사용자 조회
     */
    Optional<User> findByNickname(String nickname);
    
    /**
     * 이메일과 닉네임으로 사용자 조회 (회원가입 검증용)
     */
    @Query("SELECT u FROM User u WHERE u.email = :email OR u.nickname = :nickname")
    Optional<User> findByEmailOrNickname(@Param("email") String email, @Param("nickname") String nickname);
    
    /**
     * 활성 사용자 수 조회 (관리용)
     */
    @Query("SELECT COUNT(u) FROM User u")
    long countActiveUsers();
}