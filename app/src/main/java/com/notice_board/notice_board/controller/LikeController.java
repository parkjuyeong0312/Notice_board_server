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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class LikeController {
    
    //[TODO] LikeService 의존성 주입(나중에 구현)
    //private final LikeService likeService;

    /*
     * 게시글 좋아요 추가
     * POST /api/v1/posts/{postId}/likes
     */
    @PostMapping("/posts/{postId}/likes")
    public ResponseEntity<Map<String, Object>> addLike(@PathVariable Long postId) {
        
        //[TODO] 좋아요 추가 로직 구현
        //[TODO] 인증된 사용자 정보 가져오기
        //[TODO] 게시글 존재 여부 확인
        //[TODO] 중복 좋아요 방지 (이미 좋아요한 경우 처리)
        Map<String, Object> response = Map.of(
            "message", "좋아요 추가 성공 - 구현예정",
            "data", Map.of(
                "postId", postId,
                "userId", 1L,
                "liked", true,
                "totalLikes", 5  // 임시 값
            )
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*
     * 게시글 좋아요 취소
     * DELETE /api/v1/posts/{postId}/likes
     */
    @DeleteMapping("/posts/{postId}/likes")
    public ResponseEntity<Map<String, Object>> removeLike(@PathVariable Long postId) {
        
        //[TODO] 좋아요 취소 로직 구현
        //[TODO] 인증된 사용자 정보 가져오기
        //[TODO] 게시글 존재 여부 확인
        //[TODO] 좋아요 존재 여부 확인
        Map<String, Object> response = Map.of(
            "message", "좋아요 취소 성공 - 구현예정",
            "data", Map.of(
                "postId", postId,
                "userId", 1L,
                "liked", false,
                "totalLikes", 4  // 임시 값
            )
        );
        
        return ResponseEntity.ok(response);
    }

    /*
     * 특정 게시글의 좋아요 수 및 상태 조회
     * GET /api/v1/posts/{postId}/likes
     */
    @GetMapping("/posts/{postId}/likes")
    public ResponseEntity<Map<String, Object>> getLikeStatus(@PathVariable Long postId) {
        
        //[TODO] 좋아요 상태 조회 로직 구현
        //[TODO] 인증된 사용자의 좋아요 여부 확인
        Map<String, Object> response = Map.of(
            "message", "좋아요 상태 조회 - 구현예정",
            "data", Map.of(
                "postId", postId,
                "totalLikes", 5,
                "isLiked", false,  // 현재 사용자가 좋아요 했는지
                "recentLikes", List.of(
                    // 최근 좋아요한 사용자들 (간단 정보)
                    Map.of("userId", 2L, "nickname", "사용자A"),
                    Map.of("userId", 3L, "nickname", "사용자B"),
                    Map.of("userId", 4L, "nickname", "사용자C")
                )
            )
        );
        
        return ResponseEntity.ok(response);
    }

    /*
     * 사용자가 좋아요한 게시글 목록 조회 (간단 버전)
     * GET /api/v1/users/{userId}/likes
     */
    @GetMapping("/users/{userId}/likes")
    public ResponseEntity<Map<String, Object>> getUserLikes(@PathVariable Long userId) {
        
        //[TODO] 사용자 좋아요 목록 조회 로직 구현
        Map<String, Object> response = Map.of(
            "message", "사용자 좋아요 목록 조회 - 구현예정",
            "data", List.of(
                // 임시 데이터
                Map.of(
                    "postId", 1L,
                    "postTitle", "좋아요한 게시글 1",
                    "likedAt", "2024-01-01T10:00:00"
                ),
                Map.of(
                    "postId", 2L,
                    "postTitle", "좋아요한 게시글 2", 
                    "likedAt", "2024-01-01T11:00:00"
                )
            ),
            "userId", userId,
            "totalLikes", 2
        );
        
        return ResponseEntity.ok(response);
    }
}
