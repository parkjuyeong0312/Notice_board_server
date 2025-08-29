package com.notice_board.notice_board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notice_board.notice_board.entity.Comment;

@Repository
//Post entity에 대한 조회를 할 것이고, Long은 Comment의 id의 타입
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    //특정 게시글의 댓글 목록 조회
    Page<Comment> findByPostIdOrderByCreatedAtDesc(Long postId, Pageable pageable);

    //특정 사용자가 작성한 댓글 목록 조회
    List<Comment> findByUserIdOrderByCreatedAtDesc(Long userId);

    //특정 게시글의 댓글 개수 조회
    long countByPostId(Long postId);

    //특정 사용자가 작성한 댓글 개수 조회
    long countByUserId(Long userId);

    //특정 게시글의 댓글 조회
    boolean existsByPostId(Long postId);

    //특정 사용자가 작성한 댓글 조회
    boolean existsByPostIdAndUserId(Long postId, Long userId);
}
