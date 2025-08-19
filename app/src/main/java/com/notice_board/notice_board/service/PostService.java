package com.notice_board.notice_board.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.notice_board.notice_board.entity.Post;

public interface PostService {
    
    /**
     * 게시글 작성
     */
    Post createPost(String title, String content, Long userId);
    
    /**
     * 게시글 목록 조회 (페이징)
     */
    Page<Post> getAllPosts(Pageable pageable);
    
    /**
     * 게시글 검색 (제목 또는 내용)
     */
    Page<Post> searchPosts(String keyword, Pageable pageable);
    
    /**
     * 특정 게시글 조회
     */
    Optional<Post> getPostById(Long postId);
    
    /**
     * 게시글 수정
     */
    Post updatePost(Long postId, String title, String content, Long userId);
    
    /**
     * 게시글 삭제
     */
    void deletePost(Long postId, Long userId);
    
    /**
     * 특정 사용자의 게시글 목록 조회
     */
    Page<Post> getPostsByUserId(Long userId, Pageable pageable);
    
    /**
     * 사용자의 게시글 수 조회
     */
    long getPostCountByUserId(Long userId);
    
    /**
     * 인기 게시글 조회 (좋아요 수 기준)
     */
    Page<Post> getPopularPosts(Pageable pageable);
    
    /**
     * 게시글 작성자 확인
     */
    boolean isPostOwner(Long postId, Long userId);
}