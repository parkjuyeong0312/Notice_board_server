package com.notice_board.notice_board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.notice_board.notice_board.entity.Comment;

public interface CommentService {
    
    /**
     * 댓글 작성
     */
    Comment createComment(Long postId, String content, Long userId);
    
    /**
     * 특정 게시글의 댓글 목록 조회 (페이징)
     */
    Page<Comment> getCommentsByPostId(Long postId, Pageable pageable);
    
    /**
     * 특정 댓글 조회
     */
    Optional<Comment> getCommentById(Long commentId);
    
    /**
     * 댓글 수정
     */
    Comment updateComment(Long commentId, String content, Long userId);
    
    /**
     * 댓글 삭제
     */
    void deleteComment(Long commentId, Long userId);
    
    /**
     * 특정 사용자의 댓글 목록 조회
     */
    Page<Comment> getCommentsByUserId(Long userId, Pageable pageable);
    
    /**
     * 특정 게시글의 댓글 수 조회
     */
    long getCommentCountByPostId(Long postId);
    
    /**
     * 특정 사용자의 댓글 수 조회
     */
    long getCommentCountByUserId(Long userId);
    
    /**
     * 특정 게시글의 최근 댓글들 조회 (제한된 개수)
     */
    List<Comment> getRecentCommentsByPostId(Long postId);
    
    /**
     * 댓글 작성자 확인
     */
    boolean isCommentOwner(Long commentId, Long userId);
    
    /**
     * 게시글과 댓글 관계 확인
     */
    boolean isCommentOfPost(Long commentId, Long postId);
}