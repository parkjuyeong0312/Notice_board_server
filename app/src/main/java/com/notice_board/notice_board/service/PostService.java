package com.notice_board.notice_board.service;

import java.util.List;
import java.util.Map;

public interface PostService {
    
    // ===== CREATE =====
    /**
     * [Create] 게시글 작성
     * 새로운 게시글을 생성합니다.
     */
    Map<String, Object> createPost(String title, String content, Long userId);
    
    // ===== READ =====
    /**
     * [Read] 게시글 목록 조회
     * 페이지네이션을 적용하여 게시글 목록을 조회합니다.
     */
    List<Map<String, Object>> getAllPosts(int page, int size);
    
    /**
     * [Read] 게시글 검색 (제목 또는 내용)
     * 키워드를 통해 게시글을 검색합니다.
     */
    List<Map<String, Object>> searchPosts(String keyword, int page, int size);
    
    /**
     * [Read] 특정 게시글 조회
     * 게시글 ID를 통해 특정 게시글을 조회합니다.
     */
    Map<String, Object> getPostById(Long postId);
    
    // ===== UPDATE =====
    /**
     * [Update] 게시글 수정
     * 기존 게시글의 제목과 내용을 수정합니다.
     */
    Map<String, Object> updatePost(Long postId, String title, String content, Long userId);
    
    // ===== DELETE =====
    /**
     * [Delete] 게시글 삭제
     * 게시글을 시스템에서 삭제합니다.
     */
    void deletePost(Long postId, Long userId);
}