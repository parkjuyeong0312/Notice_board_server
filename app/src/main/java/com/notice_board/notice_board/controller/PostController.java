package com.notice_board.notice_board.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    // Task 1.3: 기본 컨트롤러 구조만 구현
    @GetMapping
    public Map<String, Object> getPosts() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "게시글 목록 조회 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @PostMapping
    public Map<String, Object> createPost() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "게시글 작성 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @GetMapping("/{postId}")
    public Map<String, Object> getPost(@PathVariable Long postId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "게시글 상세 조회 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @PutMapping("/{postId}")
    public Map<String, Object> updatePost(@PathVariable Long postId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "게시글 수정 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @DeleteMapping("/{postId}")
    public Map<String, Object> deletePost(@PathVariable Long postId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "게시글 삭제 API - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }
}
