package com.notice_board.notice_board.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    // Task 1.3: 기본 구조만 구현
    @Override
    public Map<String, Object> createComment(String content, Long postId, Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "댓글 작성 서비스 - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @Override
    public Map<String, Object> getCommentById(Long commentId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "댓글 조회 서비스 - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @Override
    public List<Map<String, Object>> getCommentsByPostId(Long postId, int page, int size) {
        // Task 1.3: 기본 구조만 구현
        return List.of();
    }

    @Override
    public Map<String, Object> updateComment(Long commentId, String content, Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "댓글 수정 서비스 - 기본 구조만 구현됨");
        response.put("data", null);
        return response;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {
        // Task 1.3: 기본 구조만 구현
    }
}