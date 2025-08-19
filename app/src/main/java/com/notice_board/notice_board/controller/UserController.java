package com.notice_board.notice_board.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {
    //private final UserService userService;

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
