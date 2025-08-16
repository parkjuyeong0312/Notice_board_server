package com.notice_board.notice_board.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    //[TODO] AuthService 의존성 주입(나중에 구현)
    //private final AuthService authService;

    /*
     * 회원가입
     * POST /api/v1/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, Object> registerData) {
        
        //[TODO] 회원가입 로직 구현
        //[TODO] 이메일 중복 검사
        //[TODO] 비밀번호 암호화
        //[TODO] 사용자 생성
        Map<String, Object> response = Map.of(
            "message", "회원가입 성공 - 구현예정",
            "data", Map.of(
                "id", 1L,
                "email", registerData.get("email"),
                "nickname", registerData.get("nickname"),
                "name", registerData.get("name")
            )
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*
     * 로그인
     * POST /api/v1/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> loginData) {
        
        //[TODO] 로그인 로직 구현
        //[TODO] 이메일/비밀번호 검증
        //[TODO] JWT 토큰 생성
        Map<String, Object> response = Map.of(
            "message", "로그인 성공 - 구현예정",
            "data", Map.of(
                "user", Map.of(
                    "id", 1L,
                    "email", loginData.get("email"),
                    "nickname", "테스트유저"
                ),
                "accessToken", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...", // 임시 토큰
                "tokenType", "Bearer",
                "expiresIn", 3600
            )
        );
        
        return ResponseEntity.ok(response);
    }

    /*
     * 로그아웃
     * POST /api/v1/auth/logout
     */
    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        
        //[TODO] 로그아웃 로직 구현
        //[TODO] 토큰 블랙리스트 추가 (선택사항)
        Map<String, Object> response = Map.of(
            "message", "로그아웃 성공 - 구현예정",
            "data", Map.of("loggedOut", true)
        );
        
        return ResponseEntity.ok(response);
    }

    /*
     * 현재 사용자 정보 조회
     * GET /api/v1/auth/me
     */
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser(
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        
        //[TODO] 현재 사용자 정보 조회 로직 구현
        //[TODO] JWT 토큰에서 사용자 정보 추출
        Map<String, Object> response = Map.of(
            "message", "현재 사용자 정보 조회 - 구현예정",
            "data", Map.of(
                "id", 1L,
                "email", "user@example.com",
                "nickname", "테스트유저",
                "name", "홍길동",
                "createdAt", "2024-01-01T10:00:00"
            )
        );
        
        return ResponseEntity.ok(response);
    }
}