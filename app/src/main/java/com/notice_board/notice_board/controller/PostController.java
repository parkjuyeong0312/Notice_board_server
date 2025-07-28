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
@RequestMapping("/posts")
@CrossOrigin(origins = "localhost:3000")
public class PostController {
    //[TODO] PostService 의존성 주입(나중에 구현)
    //private final PostService postService;

    /*
     * 게시글 목록 조회
     * GET /posts
     * params: page, size, search, sort
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPosts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "") String search,
        @RequestParam(defaultValue = "created_at") String sort,
        @RequestParam(defaultValue = "desc") String direction
    ) {
        //[TODO] 게시글 목록 조회 로직 구현
        Map<String, Object> response = Map. of (
            "message", "게시글 목록 조회 - 구현 예정",
            "data", List.of(),// 임시 빈 배열 반환
            "page", page,
            "size",size,
            "totalElements", 0,
            "totalPages", 0
        );

        return ResponseEntity.ok(response);
    }

    /*
     * 특정 게시글 조회
     * GET /posts/{postId}
     */

    @GetMapping("/{postId}")
    public ResponseEntity<Map<String, Object>> getPostById(@PathVariable Long postId) {
        //[TODO] 특정 게시글 조회 로직 구현
        Map<String, Object> response = Map.of(
            "message", "게시글 상세조회 - 구현예정",
            "data", Map.of("id", postId)
        );
        return ResponseEntity.ok(response);
    }

    /*
     * 게시글 생성
     * POST /posts
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createPost(
        @RequestBody Map<String, Object> postData
    ) {
        //[TODO] 게시글 생성 로직 구현
        Map<String, Object> response = Map.of(
            "message", "게시글 생성 - 구현예정",
            "data", Map.of("id", 1L, "title", postData.get("title"))
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*
     * 게시글 수정
     * PUT /posts/{postId}
     */
    @PutMapping("/{postId}")
    public ResponseEntity<Map<String, Object>> updatePost(
        @PathVariable Long postId,
        @RequestBody Map<String, Object> postData
    ){
        //[TODO] 게시글 수정 로직 구현
        //[TODO] 작성자 권한 확인
        Map<String, Object> response = Map.of(
            "message", "게시글 수정 - 구현예정",
            "data", Map.of("id", postId, "title", postData.get("title"))
        );

        return ResponseEntity.ok(response);
    }

    /*
     * 게시글 삭제
     * DELETE /posts/{postId}
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<Map<String, Object>> deletePost(@PathVariable Long postId) {
        //[TODO] 게시글 삭제 로직 구현
        //[TODO] 작성자 권한 확인
        Map<String, Object> response = Map.of(
            "message", "게시글 삭제 - 구현예정",
            "data", Map.of("id", postId)
        );
        return ResponseEntity.ok(response);
    }

    /*
     * 특정 게시글 댓글 목록 조회
     * GET /posts/{postId}/comments
     */
    @GetMapping("/{postId}/comments")
    public ResponseEntity<Map<String, Object>> getCommentsByPostId(
        @PathVariable Long postId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {

        Map<String, Object> response = Map.of(
            "message", "댓글 목록 조회 - 구현예정",
            "data", List.of(),
            "postId", postId,
            "page", page,
            "size", size
        );
        return ResponseEntity.ok(response);
    }
    /*
     * 특정 게시글의 좋아요 목록 조회
     * GET /posts/{postId}/likes
     */
    @GetMapping("/{postId}/likes")
    public ResponseEntity<Map<String, Object>> getLikesByPostId(
        @PathVariable Long postId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {

        Map<String, Object> response = Map.of(
            "message", "좋아요 목록 조회 - 구현예정",
            "data", List.of(),
            "postId", postId,
            "totalLikes", 0
        );
        return ResponseEntity.ok(response);
    }
}
