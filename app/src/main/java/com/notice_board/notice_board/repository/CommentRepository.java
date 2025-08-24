package com.notice_board.notice_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.notice_board.notice_board.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    // Task 1.3: 기본 구조만 구현
    // Phase 2에서 필요한 쿼리 메서드들 추가 예정
}