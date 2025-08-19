package com.notice_board.notice_board.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notice_board.notice_board.entity.Post;
import com.notice_board.notice_board.entity.User;
import com.notice_board.notice_board.repository.PostRepository;
import com.notice_board.notice_board.repository.UserRepository;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public Post createPost(String title, String content, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
        
        Post post = Post.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
        
        return postRepository.save(post);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAllByOrderByCreateAtDesc(pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Post> searchPosts(String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllPosts(pageable);
        }
        return postRepository.findByTitleOrContentContaining(keyword.trim(), pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }
    
    @Override
    public Post updatePost(Long postId, String title, String content, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + postId));
        
        // 작성자 확인
        if (!post.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("게시글 수정 권한이 없습니다");
        }
        
        // 게시글 수정 (Post 엔티티에 setter나 update 메서드가 필요)
        // 현재는 Builder 패턴만 있으므로 새로운 Post 객체 생성
        Post updatedPost = Post.builder()
                .id(post.getId())
                .title(title)
                .content(content)
                .user(post.getUser())
                .createAt(post.getCreateAt())
                .updatedAt(post.getUpdatedAt())
                .comments(post.getComments())
                .likes(post.getLikes())
                .build();
        
        return postRepository.save(updatedPost);
    }
    
    @Override
    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + postId));
        
        // 작성자 확인
        if (!post.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("게시글 삭제 권한이 없습니다");
        }
        
        postRepository.delete(post);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Post> getPostsByUserId(Long userId, Pageable pageable) {
        return postRepository.findByUserIdOrderByCreateAtDesc(userId, pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long getPostCountByUserId(Long userId) {
        return postRepository.countByUserId(userId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Post> getPopularPosts(Pageable pageable) {
        return postRepository.findPopularPosts(pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isPostOwner(Long postId, Long userId) {
        Optional<Post> post = postRepository.findById(postId);
        return post.isPresent() && post.get().getUser().getId().equals(userId);
    }
}