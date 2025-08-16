package com.notice_board.notice_board.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {
    //[TODO] UserService 의존성 주입(나중에 구현)
    //private final UserService userService;

    /*
     * 회원 가입
     * POST /users
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> registerUser(
        @RequestBody Map<String, Object> userData){
            //[TODO] 회원가입 로직 구현
            //[TODO] 이메일 중복 검사
            //[TODO] 비밀번호 암호화
            Map<String, Object> response = Map.of(
                "message", "User registered successfully",
                "data", Map.of(
                    "id", 1L,
                    "email", userData.get("email"),
                    "nickname", userData.get("nickname")
                )
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

    /*
     * 사용자 조회
     * GET /users/{userId}
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long userId){
        //[TODO] 사용자 조회 로직 구현
        Map<String, Object> response = Map.of(
            "message", "User fetched successfully",
            "data", Map.of(
                "id", userId,
                "email", "user@example.com",
                "nickname", "user123"
            )
        );
        return ResponseEntity.ok(response);
    }
    // [TODO] 사용자 정보 수정 API
    // [TODO] 회원 탈퇴 API
    // [TODO] 사용자 목록 조회 API (관리자용)
}
