package com.notice_board.notice_board.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
public class CommentController {

    // Task 1.3: 기본 컨트롤러 구조만 구현
    @GetMapping
    public Map<String, Object> getComments(@PathVariable Long postId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "댓글 목록 조회 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @PostMapping
    public Map<String, Object> createComment(@PathVariable Long postId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "댓글 작성 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @PutMapping("/{commentId}")
    public Map<String, Object> updateComment(@PathVariable Long postId, @PathVariable Long commentId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "댓글 수정 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @DeleteMapping("/{commentId}")
    public Map<String, Object> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "댓글 삭제 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }
}
