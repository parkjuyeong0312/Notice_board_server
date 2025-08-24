package com.notice_board.notice_board.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    // Task 1.3: 기본 구조만 구현
    @Override
    public Map<String, Object> createPost(String title, String content, Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "게시글 작성 서비스 - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @Override
    public Map<String, Object> getPostById(Long postId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "게시글 조회 서비스 - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @Override
    public List<Map<String, Object>> getAllPosts(int page, int size) {
        // Task 1.3: 기본 구조만 구현
        return List.of();
    }

    @Override
    public Map<String, Object> updatePost(Long postId, String title, String content, Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "게시글 수정 서비스 - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @Override
    public void deletePost(Long postId, Long userId) {
        // Task 1.3: 기본 구조만 구현
    }

    @Override
    public List<Map<String, Object>> searchPosts(String keyword, int page, int size) {
        // Task 1.3: 기본 구조만 구현
        return List.of();
    }
}