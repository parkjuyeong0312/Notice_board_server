package com.notice_board.notice_board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.notice_board.notice_board.entity.Post;
import com.notice_board.notice_board.entity.User;
import com.notice_board.notice_board.repository.PostRepository;
import com.notice_board.notice_board.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

//매서드 목록
//[CREATE] createPost
//[READ] getPostById, getAllPosts, searchPosts
//[UPDATE] updatePost
//[DELETE] deletePost

@Service
public class PostServiceImpl implements PostService {

    // 1. Repository 연결
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
   
    // == [CREATE] ==
    /*
     * [게시글 작성하기]
     */
     @Override
     public Map<String, Object> createPost(String title, String content, Long userId){
        // response
        Map<String, Object> response = new HashMap<>();

        try{
            //1. 사용자 존재 확인
            Optional<User> userOpt = userRepository.findById(userId);
            if(userOpt.isEmpty()){
                response.put("success", false);
                response.put("message", "사용자가 존재하지 않습니다.");
                response.put("data", null);
                return response;
            }

            User user = userOpt.get();

            //게시글 생성
            Post post = new Post(title, content, user);
            Post savedPost = postRepository.save(post);

            //응답 데이터 구성
            Map<String, Object> postData = new HashMap<>();
            postData.put("id", savedPost.getId());
            postData.put("title", savedPost.getTitle());
            postData.put("content", savedPost.getContent());
            postData.put("userId", savedPost.getUser().getId());
            postData.put("createdAt", savedPost.getCreatedAt());

            response.put("success", true);
            response.put("message", "게시글 작성 성공");
            response.put("data", postData);

            return response;
        }catch(Exception e){
            response.put("success", false);
            response.put("message", "게시글 작성 중 오류가 발생했습니다.");
            response.put("data", null);
            return response;
        }
     }

     // == [READ] ==
     //페이지네이션 사용하여 모든 게시글 목록 조회하기
     @Override
     public List<Map<String, Object>> getAllPosts(int page, int size){
        try{
            //페이징 처리
            Pageable pageable = PageRequest.of(page, size);
            Page<Post> postPage = postRepository.findAllByOrderByCreatedAtDesc(pageable);
            
            //Map 형태로 반환
            return postPage.getContent().stream()
                .map(post->{
                    Map<String, Object> postMap = new HashMap<>();
                    postMap.put("id", post.getId());
                    postMap.put("title", post.getTitle());
                    postMap.put("content", post.getContent());
                    postMap.put("userId", post.getUser().getId());
                    postMap.put("createdAt", post.getCreatedAt());
                    postMap.put("updatedAt", post.getUpdatedAt());
                    return postMap;
                }).toList();
        }catch(Exception e){
            // 오류 발생 시 빈 리스트 반환
            return List.of();
        }
     }

     /**
      * [Read] 게시글 검색 (제목 또는 내용)
      * 키워드를 통해 게시글을 검색합니다.
      */
     @Override
     public List<Map<String, Object>> searchPosts(String keyword, int page, int size) {
         try {
             // 페이징 처리
             Pageable pageable = PageRequest.of(page, size);
             Page<Post> postPage = postRepository.findByTitleContainingOrContentContaining(keyword, pageable);
             
             // Map 형태로 변환
             return postPage.getContent().stream()
                 .map(post -> {
                     Map<String, Object> postMap = new HashMap<>();
                     postMap.put("id", post.getId());
                     postMap.put("title", post.getTitle());
                     postMap.put("content", post.getContent());
                     postMap.put("userId", post.getUser().getId());
                     postMap.put("createdAt", post.getCreatedAt());
                     postMap.put("updatedAt", post.getUpdatedAt());
                     return postMap;
                 })
                 .toList();
                 
         } catch (Exception e) {
             // 오류 발생 시 빈 리스트 반환
             return List.of();
         }
     }

     /**
      * [Read] 특정 게시글 조회
      * 게시글 ID를 통해 특정 게시글을 조회합니다.
      */
     @Override
     public Map<String, Object> getPostById(Long postId) {
         Map<String, Object> response = new HashMap<>();
         
         try {
             // 게시글 조회
             Optional<Post> postOpt = postRepository.findById(postId);
             if (postOpt.isEmpty()) {
                 response.put("success", false);
                 response.put("message", "존재하지 않는 게시글입니다.");
                 response.put("data", null);
                 return response;
             }
             
             Post post = postOpt.get();
             
             // 응답 데이터 구성
             Map<String, Object> postData = new HashMap<>();
             postData.put("id", post.getId());
             postData.put("title", post.getTitle());
             postData.put("content", post.getContent());
             postData.put("userId", post.getUser().getId());
             postData.put("createdAt", post.getCreatedAt());
             postData.put("updatedAt", post.getUpdatedAt());
             
             response.put("success", true);
             response.put("message", "게시글 조회가 완료되었습니다.");
             response.put("data", postData);
             
         } catch (Exception e) {
             response.put("success", false);
             response.put("message", "게시글 조회 중 오류가 발생했습니다: " + e.getMessage());
             response.put("data", null);
         }
         
         return response;
     }

     // == [UPDATE] ==
     /**
      * [Update] 게시글 수정
      * 기존 게시글의 제목과 내용을 수정합니다.
      */
     @Override
     public Map<String, Object> updatePost(Long postId, String title, String content, Long userId) {
         Map<String, Object> response = new HashMap<>();
         
         try {
             // 게시글 조회
             Optional<Post> postOpt = postRepository.findById(postId);
             if (postOpt.isEmpty()) {
                 response.put("success", false);
                 response.put("message", "존재하지 않는 게시글입니다.");
                 response.put("data", null);
                 return response;
             }
             
             Post post = postOpt.get();
             
             // 작성자 확인
             if (!post.getUser().getId().equals(userId)) {
                 response.put("success", false);
                 response.put("message", "게시글을 수정할 권한이 없습니다.");
                 response.put("data", null);
                 return response;
             }
             
             // 게시글 수정
             post.setTitle(title);
             post.setContent(content);
             post.setUpdatedAt(LocalDateTime.now());
             
             Post updatedPost = postRepository.save(post);
             
             // 응답 데이터 구성
             Map<String, Object> postData = new HashMap<>();
             postData.put("id", updatedPost.getId());
             postData.put("title", updatedPost.getTitle());
             postData.put("content", updatedPost.getContent());
             postData.put("userId", updatedPost.getUser().getId());
             postData.put("createdAt", updatedPost.getCreatedAt());
             postData.put("updatedAt", updatedPost.getUpdatedAt());
             
             response.put("success", true);
             response.put("message", "게시글이 성공적으로 수정되었습니다.");
             response.put("data", postData);
             
         } catch (Exception e) {
             response.put("success", false);
             response.put("message", "게시글 수정 중 오류가 발생했습니다: " + e.getMessage());
             response.put("data", null);
         }
         
         return response;
     }

     // == [DELETE] ==
     /**
      * [Delete] 게시글 삭제
      * 게시글을 시스템에서 삭제합니다.
      */
     @Override
     public void deletePost(Long postId, Long userId) {
         try {
             // 게시글 조회
             Optional<Post> postOpt = postRepository.findById(postId);
             if (postOpt.isEmpty()) {
                 throw new RuntimeException("존재하지 않는 게시글입니다.");
             }
             
             Post post = postOpt.get();
             
             // 작성자 확인
             if (!post.getUser().getId().equals(userId)) {
                 throw new RuntimeException("게시글을 삭제할 권한이 없습니다.");
             }
             
             // 게시글 삭제
             postRepository.delete(post);
             
         } catch (Exception e) {
             throw new RuntimeException("게시글 삭제 중 오류가 발생했습니다: " + e.getMessage());
         }
     }
}