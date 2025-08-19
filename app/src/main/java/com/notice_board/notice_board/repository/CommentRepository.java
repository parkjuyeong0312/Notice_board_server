package com.notice_board.notice_board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.notice_board.notice_board.entity.Comment;
import com.notice_board.notice_board.entity.Post;
import com.notice_board.notice_board.entity.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    /**
     * 특정 게시글의 댓글 목록 조회 (페이징)
     */
    Page<Comment> findByPostOrderByCreatedAtAsc(Post post, Pageable pageable);
    
    /**
     * 특정 게시글의 댓글 목록 조회 (게시글 ID로)
     */
    Page<Comment> findByPostIdOrderByCreatedAtAsc(Long postId, Pageable pageable);
    
    /**
     * 특정 사용자의 댓글 목록 조회
     */
    Page<Comment> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
    
    /**
     * 특정 사용자의 댓글 목록 조회 (사용자 ID로)
     */
    Page<Comment> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
    
    /**
     * 특정 게시글의 댓글 수 조회
     */
    long countByPostId(Long postId);
    
    /**
     * 특정 사용자의 댓글 수 조회
     */
    long countByUserId(Long userId);
    
    /**
     * 특정 게시글의 최근 댓글들 조회 (제한된 개수)
     */
    List<Comment> findTop5ByPostIdOrderByCreatedAtDesc(Long postId);
    
    /**
     * 게시글과 사용자로 댓글 존재 여부 확인
     */
    boolean existsByPostIdAndUserId(Long postId, Long userId);
    
    /**
     * 전체 댓글 수 조회
     */
    @Query("SELECT COUNT(c) FROM Comment c")
    long countAllComments();
    
    /**
     * 특정 기간 동안의 댓글 수 조회 (관리용)
     */
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.createdAt >= :startDate")
    long countCommentsAfterDate(@Param("startDate") java.time.LocalDateTime startDate);
}