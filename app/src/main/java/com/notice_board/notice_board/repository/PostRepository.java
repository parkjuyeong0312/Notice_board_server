package com.notice_board.notice_board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.notice_board.notice_board.entity.Post;
import com.notice_board.notice_board.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
    /**
     * 페이징으로 게시글 목록 조회 (최신순)
     */
    Page<Post> findAllByOrderByCreateAtDesc(Pageable pageable);
    
    /**
     * 제목으로 게시글 검색 (페이징)
     */
    Page<Post> findByTitleContainingIgnoreCaseOrderByCreateAtDesc(String title, Pageable pageable);
    
    /**
     * 내용으로 게시글 검색 (페이징)
     */
    Page<Post> findByContentContainingIgnoreCaseOrderByCreateAtDesc(String content, Pageable pageable);
    
    /**
     * 제목 또는 내용으로 게시글 검색 (페이징)
     */
    @Query("SELECT p FROM Post p WHERE " +
           "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "ORDER BY p.createAt DESC")
    Page<Post> findByTitleOrContentContaining(@Param("keyword") String keyword, Pageable pageable);
    
    /**
     * 특정 사용자의 게시글 목록 조회
     */
    Page<Post> findByUserOrderByCreateAtDesc(User user, Pageable pageable);
    
    /**
     * 특정 사용자의 게시글 목록 조회 (사용자 ID로)
     */
    Page<Post> findByUserIdOrderByCreateAtDesc(Long userId, Pageable pageable);
    
    /**
     * 특정 사용자의 게시글 수 조회
     */
    long countByUserId(Long userId);
    
    /**
     * 전체 게시글 수 조회
     */
    @Query("SELECT COUNT(p) FROM Post p")
    long countAllPosts();
    
    /**
     * 인기 게시글 조회 (좋아요 수 기준, 추후 구현)
     */
    @Query("SELECT p FROM Post p LEFT JOIN p.likes l " +
           "GROUP BY p.id " +
           "ORDER BY COUNT(l) DESC, p.createAt DESC")
    Page<Post> findPopularPosts(Pageable pageable);
}