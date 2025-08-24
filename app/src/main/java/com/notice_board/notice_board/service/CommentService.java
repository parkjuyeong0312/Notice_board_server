package com.notice_board.notice_board.service;

import java.util.List;
import java.util.Map;

public interface CommentService {
    
    // ===== CREATE =====
    /**
     * [Create] 댓글 작성
     * 새로운 댓글을 생성합니다.
     */
    Map<String, Object> createComment(String content, Long postId, Long userId);
    
    // ===== READ =====
    /**
     * [Read] 특정 댓글 조회
     * 댓글 ID를 통해 특정 댓글을 조회합니다.
     */
    Map<String, Object> getCommentById(Long commentId);
    
    /**
     * [Read] 특정 게시글의 댓글 목록 조회
     * 게시글 ID를 통해 해당 게시글의 댓글 목록을 조회합니다.
     */
    List<Map<String, Object>> getCommentsByPostId(Long postId, int page, int size);
    
    // ===== UPDATE =====
    /**
     * [Update] 댓글 수정
     * 기존 댓글의 내용을 수정합니다.
     */
    Map<String, Object> updateComment(Long commentId, String content, Long userId);
    
    // ===== DELETE =====
    /**
     * [Delete] 댓글 삭제
     * 댓글을 시스템에서 삭제합니다.
     */
    void deleteComment(Long commentId, Long userId);
}