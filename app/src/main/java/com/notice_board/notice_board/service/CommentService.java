package com.notice_board.notice_board.service;

import java.util.List;
import java.util.Map;

public interface CommentService {
    
    /**
     * 댓글 작성
     */
    Map<String, Object> createComment(String content, Long postId, Long userId);
    
    /**
     * 특정 댓글 조회
     */
    Map<String, Object> getCommentById(Long commentId);
    
    /**
     * 특정 게시글의 댓글 목록 조회
     */
    List<Map<String, Object>> getCommentsByPostId(Long postId, int page, int size);
    
    /**
     * 댓글 수정
     */
    Map<String, Object> updateComment(Long commentId, String content, Long userId);
    
    /**
     * 댓글 삭제
     */
    void deleteComment(Long commentId, Long userId);
}