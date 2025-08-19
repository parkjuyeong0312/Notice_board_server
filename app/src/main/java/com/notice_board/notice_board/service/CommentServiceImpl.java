package com.notice_board.notice_board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notice_board.notice_board.entity.Comment;
import com.notice_board.notice_board.entity.Post;
import com.notice_board.notice_board.entity.User;
import com.notice_board.notice_board.repository.CommentRepository;
import com.notice_board.notice_board.repository.PostRepository;
import com.notice_board.notice_board.repository.UserRepository;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, 
                             PostRepository postRepository, 
                             UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public Comment createComment(Long postId, String content, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + postId));
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
        
        Comment comment = Comment.builder()
                .content(content)
                .post(post)
                .user(user)
                .build();
        
        return commentRepository.save(comment);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Comment> getCommentsByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId, pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }
    
    @Override
    public Comment updateComment(Long commentId, String content, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다: " + commentId));
        
        // 작성자 확인
        if (!comment.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("댓글 수정 권한이 없습니다");
        }
        
        // 댓글 수정 (Comment 엔티티에 setter나 update 메서드가 필요)
        // 현재는 Builder 패턴만 있으므로 새로운 Comment 객체 생성
        Comment updatedComment = Comment.builder()
                .id(comment.getId())
                .content(content)
                .post(comment.getPost())
                .user(comment.getUser())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
        
        return commentRepository.save(updatedComment);
    }
    
    @Override
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다: " + commentId));
        
        // 작성자 확인
        if (!comment.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("댓글 삭제 권한이 없습니다");
        }
        
        commentRepository.delete(comment);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Comment> getCommentsByUserId(Long userId, Pageable pageable) {
        return commentRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long getCommentCountByPostId(Long postId) {
        return commentRepository.countByPostId(postId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long getCommentCountByUserId(Long userId) {
        return commentRepository.countByUserId(userId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Comment> getRecentCommentsByPostId(Long postId) {
        return commentRepository.findTop5ByPostIdOrderByCreatedAtDesc(postId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isCommentOwner(Long commentId, Long userId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        return comment.isPresent() && comment.get().getUser().getId().equals(userId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isCommentOfPost(Long commentId, Long postId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        return comment.isPresent() && comment.get().getPost().getId().equals(postId);
    }
}