package com.notice_board.notice_board.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.notice_board.notice_board.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    // 페이징을 적용한 게시글 목록 조회(최신순)
    //Page는 페이징 처리된 결과를 담는 객체
    //Pageable은 요청할 페이지 정보를 담는 인터페이스
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // 제목 또는 내용에 키워드가 포함된 게시글 검색 + 페이징
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword% ORDER BY p.createdAt DESC")
    Page<Post> findByTitleContainingOrContentContaining(@Param("keyword") String keyword, Pageable pageable);

    // 특정 사용자가 작성한 게시글 목록 조회
    Page<Post> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
    
    // 제목으로 게시글 검색
    Page<Post> findByTitleContaining(String title, Pageable pageable);

    // 내용으로 게시글 검색
    Page<Post> findByContentContaining(String content, Pageable pageable);

}
