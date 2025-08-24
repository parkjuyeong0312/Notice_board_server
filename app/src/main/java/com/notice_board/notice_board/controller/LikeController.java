package com.notice_board.notice_board.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts/{postId}/likes")
public class LikeController {

    // Task 1.3: 기본 컨트롤러 구조만 구현
    @PostMapping
    public Map<String, Object> addLike(@PathVariable Long postId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "좋아요 추가 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @DeleteMapping
    public Map<String, Object> removeLike(@PathVariable Long postId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "좋아요 취소 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @GetMapping
    public Map<String, Object> getLikeStatus(@PathVariable Long postId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "좋아요 상태 조회 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }
}
