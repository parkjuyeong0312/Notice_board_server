package com.notice_board.notice_board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.notice_board.notice_board.entity.Comment;
import com.notice_board.notice_board.entity.Post;
import com.notice_board.notice_board.entity.User;
import com.notice_board.notice_board.repository.CommentRepository;
import com.notice_board.notice_board.repository.PostRepository;
import com.notice_board.notice_board.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    //댓글 생성
    @Override
    public Map<String, Object> createComment(String content, Long postId, Long userId){
        Map<String, Object> response = new HashMap<>();

        try{
            //게시글과 사용자 존재 여부 확인
            //1. 게시글 존재 여부 확인
            Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

            //2. 사용자 존재 여부 확인
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

            //댓글 생성
            Comment comment = new Comment(content, post, user);
            Comment savedComment = commentRepository.save(comment);

            response.put("success", true);
            response.put("message", "댓글이 성공적으로 작성되었습니다.");
            response.put("commentId", savedComment.getId());
            response.put("data", convertCommentToMap(savedComment));

        }catch (Exception e){
            response.put("success", false);
            response.put("message", "댓글 작성중 오류가 발생했습니다."+e.getMessage());
        }

        return response;
    }
    
    //특정 댓글 id로 댓글 조회
    @Override
    public Map<String, Object> getCommentById(Long commentId){
        Map<String, Object> response = new HashMap<>();

        try{
            Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

            response.put("success", true);
            response.put("message", "댓글 조회가 완료되었습니다.");
            response.put("data", convertCommentToMap(comment));
            }catch (Exception e){
                response.put("success", false);
                response.put("message", "댓글 조회 중 오류가 발생했습니다."+e.getMessage());
        }

        return response;
    }
    
    //특정 게시글의 댓글 조회
    @Override
    public List<Map<String, Object>> getCommentsByPostId(Long postId, int page, int size){
        try{
            Pageable pageable = PageRequest.of(page,size);
            Page<Comment> commentPage = commentRepository.findByPostIdOrderByCreatedAtDesc(postId, pageable);

            return commentPage.getContent().stream()
                .map(this::convertCommentToMap)
                .collect(Collectors.toList());
        }catch (Exception e){
            throw new RuntimeException("댓글 조회 중 오류가 발생했습니다."+e.getMessage());
        }
    }

    //댓글 수정
    @Override
    public Map<String, Object> updateComment(Long commentId, String content, Long userId){
        Map<String, Object> response = new HashMap<>();

        try{
            //댓글 존재 여부 확인
            Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new RuntimeException("댓글을 찾을 수 없습니다."));

            //댓글 작성자 확인
            if(!comment.getUser().getId().equals(userId)){
                throw new RuntimeException("댓글 수정 권한이 없습니다.");
            }

            //댓글 내용 업데이트
            comment.setContent(content);
            Comment updatedComment = commentRepository.save(comment);

            response.put("success", true);
            response.put("message", "댓글이 성공적으로 수정되었습니다.");
            response.put("data", convertCommentToMap(updatedComment));
        }catch (Exception e){
            response.put("success", false);
            response.put("message", "댓글 수정 중 오류가 발생했습니다."+e.getMessage());
        }

        return response;
    }

    //댓글 삭제
    @Override
    public void deleteComment(Long commentId, Long userId){
        try{
            //댓글 존재 여부 확인
            Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new RuntimeException("댓글을 찾을 수 없습니다."));

            //댓글 작성자 확인
            if(!comment.getUser().getId().equals(userId)){
                throw new RuntimeException("댓글 삭제 권한이 없습니다.");
            }

            //댓글 삭제
            commentRepository.delete(comment);
        }catch (Exception e){
            throw new RuntimeException("댓글 삭제 중 오류가 발생했습니다."+e.getMessage());
        }
    }

    // comment 를 Map으로 변환하는 매서드
    private Map<String, Object> convertCommentToMap(Comment comment){
        Map<String, Object> commentMap = new HashMap<>();
        commentMap.put("id", comment.getId());
        commentMap.put("content", comment.getContent());
        commentMap.put("createdAt", comment.getCreatedAt());
        commentMap.put("updatedAt", comment.getUpdatedAt());
        
        // 사용자 정보
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", comment.getUser().getId());
        userMap.put("nickname", comment.getUser().getNickname());
        commentMap.put("user", userMap);
        
        // 게시글 정보
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("id", comment.getPost().getId());
        postMap.put("title", comment.getPost().getTitle());
        commentMap.put("post", postMap);
        
        return commentMap;
    }
    
}