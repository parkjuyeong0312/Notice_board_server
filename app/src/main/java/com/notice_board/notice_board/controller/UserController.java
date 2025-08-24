package com.notice_board.notice_board.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // Task 1.3: 기본 컨트롤러 구조만 구현
    @GetMapping("/{userId}")
    public Map<String, Object> getUser(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "사용자 조회 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }
}
