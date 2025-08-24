package com.notice_board.notice_board.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "likes")
public class Like {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // 기본 생성자
    public Like() {}
    
    // 생성자
    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getter/Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
