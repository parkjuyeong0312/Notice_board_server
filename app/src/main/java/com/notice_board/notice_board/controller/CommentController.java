package com.notice_board.notice_board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CommentController {
    
    //[TODO] CommentService 의존성 주입(나중에 구현)
    //private final CommentService commentService;

    /*
     * 특정 게시글의 댓글 목록 조회
     * GET /api/v1/posts/{postId}/comments
     */
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<Map<String, Object>> getCommentsByPostId(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        //[TODO] 댓글 목록 조회 로직 구현
        Map<String, Object> response = Map.of(
            "message", "댓글 목록 조회 - 구현예정",
            "data", List.of(
                // 임시 테스트 데이터
                Map.of(
                    "id", 1L,
                    "content", "첫 번째 댓글입니다.",
                    "user", Map.of("id", 1L, "nickname", "사용자1"),
                    "createdAt", "2024-01-01T10:00:00"
                ),
                Map.of(
                    "id", 2L,
                    "content", "두 번째 댓글입니다.",
                    "user", Map.of("id", 2L, "nickname", "사용자2"),
                    "createdAt", "2024-01-01T11:00:00"
                )
            ),
            "postId", postId,
            "page", page,
            "size", size,
            "totalElements", 2,
            "totalPages", 1
        );
        
        return ResponseEntity.ok(response);
    }

    /*
     * 댓글 작성
     * POST /api/v1/posts/{postId}/comments
     */
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Map<String, Object>> createComment(
            @PathVariable Long postId,
            @RequestBody Map<String, Object> commentData) {
        
        //[TODO] 댓글 작성 로직 구현
        //[TODO] 인증된 사용자 정보 가져오기
        //[TODO] 게시글 존재 여부 확인
        Map<String, Object> response = Map.of(
            "message", "댓글 작성 성공 - 구현예정",
            "data", Map.of(
                "id", 1L,
                "postId", postId,
                "content", commentData.get("content"),
                "user", Map.of("id", 1L, "nickname", "현재사용자"),
                "createdAt", "2024-01-01T12:00:00"
            )
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*
     * 댓글 수정
     * PUT /api/v1/posts/{postId}/comments/{commentId}
     */
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Map<String, Object>> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody Map<String, Object> commentData) {
        
        //[TODO] 댓글 수정 로직 구현
        //[TODO] 작성자 권한 확인
        //[TODO] 댓글 존재 여부 확인
        Map<String, Object> response = Map.of(
            "message", "댓글 수정 성공 - 구현예정",
            "data", Map.of(
                "id", commentId,
                "postId", postId,
                "content", commentData.get("content"),
                "user", Map.of("id", 1L, "nickname", "현재사용자"),
                "updatedAt", "2024-01-01T13:00:00"
            )
        );
        
        return ResponseEntity.ok(response);
    }

    /*
     * 댓글 삭제
     * DELETE /api/v1/posts/{postId}/comments/{commentId}
     */
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Map<String, Object>> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        
        //[TODO] 댓글 삭제 로직 구현
        //[TODO] 작성자 권한 또는 관리자 권한 확인
        //[TODO] 댓글 존재 여부 확인
        Map<String, Object> response = Map.of(
            "message", "댓글 삭제 성공 - 구현예정",
            "data", Map.of(
                "deletedCommentId", commentId,
                "postId", postId
            )
        );
        
        return ResponseEntity.ok(response);
    }

    /*
     * 특정 댓글 상세 조회
     * GET /api/v1/posts/{postId}/comments/{commentId}
     */
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Map<String, Object>> getCommentById(
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        
        //[TODO] 특정 댓글 조회 로직 구현
        Map<String, Object> response = Map.of(
            "message", "댓글 상세 조회 - 구현예정",
            "data", Map.of(
                "id", commentId,
                "postId", postId,
                "content", "댓글 상세 내용입니다.",
                "user", Map.of("id", 1L, "nickname", "작성자"),
                "createdAt", "2024-01-01T10:00:00",
                "updatedAt", "2024-01-01T10:00:00"
            )
        );
        
        return ResponseEntity.ok(response);
    }
}
