package com.notice_board.notice_board.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notice_board.notice_board.entity.User;
import com.notice_board.notice_board.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    // 사용자 등록
    @Override
    public User registerUser(String email, String password, String name, String nickname) {
        // 이메일 중복 검사
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + email);
        }
        
        // 닉네임 중복 검사
        if (userRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다: " + nickname);
        }
        
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);
        
        // 사용자 생성
        User user = User.builder()
                .email(email)
                .password(encodedPassword)
                .name(name)
                .nickname(nickname)
                .build();
        
        return userRepository.save(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
    
    @Override
    public User updateUser(Long userId, String name, String nickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
        
        // 닉네임이 변경되었고, 이미 사용 중인 닉네임인지 확인
        if (!user.getNickname().equals(nickname) && userRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다: " + nickname);
        }
        
        // 사용자 정보 업데이트 (User 엔티티에 setter나 update 메서드가 필요)
        // 현재는 Builder 패턴만 있으므로 새로운 User 객체 생성
        User updatedUser = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(name)
                .nickname(nickname)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .posts(user.getPosts())
                .comments(user.getComments())
                .likes(user.getLikes())
                .build();
        
        return userRepository.save(updatedUser);
    }
    
    @Override
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
        
        // 현재 비밀번호 확인
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 올바르지 않습니다");
        }
        
        // 새 비밀번호 암호화
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        
        // 비밀번호 업데이트
        User updatedUser = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(encodedNewPassword)
                .name(user.getName())
                .nickname(user.getNickname())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .posts(user.getPosts())
                .comments(user.getComments())
                .likes(user.getLikes())
                .build();
        
        userRepository.save(updatedUser);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean isNicknameExists(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
    
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
        
        // 실제 삭제 (soft delete 구현하려면 User 엔티티에 deleted 필드 추가 필요)
        userRepository.delete(user);
    }
}