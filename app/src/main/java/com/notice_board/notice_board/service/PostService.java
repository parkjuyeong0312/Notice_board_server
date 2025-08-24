package com.notice_board.notice_board.service;

import java.util.List;
import java.util.Map;

public interface PostService {
    
    /**
     * 게시글 작성
     */
    Map<String, Object> createPost(String title, String content, Long userId);
    
    /**
     * 게시글 목록 조회
     */
    List<Map<String, Object>> getAllPosts(int page, int size);
    
    /**
     * 게시글 검색 (제목 또는 내용)
     */
    List<Map<String, Object>> searchPosts(String keyword, int page, int size);
    
    /**
     * 특정 게시글 조회
     */
    Map<String, Object> getPostById(Long postId);
    
    /**
     * 게시글 수정
     */
    Map<String, Object> updatePost(Long postId, String title, String content, Long userId);
    
    /**
     * 게시글 삭제
     */
    void deletePost(Long postId, Long userId);
}