package com.notice_board.notice_board.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notice_board.notice_board.entity.User;
import com.notice_board.notice_board.repository.UserRepository;
import com.notice_board.notice_board.security.CustomUserPrincipal;
import com.notice_board.notice_board.security.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    
    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider,
                          AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public Map<String, Object> register(String email, String password, String name, String nickname) {
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
        
        User savedUser = userRepository.save(user);
        
        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("user", createUserResponse(savedUser));
        response.put("message", "회원가입이 완료되었습니다.");
        
        log.info("회원가입 완료: {}", email);
        return response;
    }
    
    @Override
    public Map<String, Object> login(String email, String password) {
        try {
            // 인증 수행
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            
            CustomUserPrincipal userPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
            
            // JWT 토큰 생성
            String accessToken = jwtTokenProvider.generateAccessToken(email, userPrincipal.getId());
            String refreshToken = jwtTokenProvider.generateRefreshToken(email, userPrincipal.getId());
            
            // 응답 데이터 구성
            Map<String, Object> response = new HashMap<>();
            response.put("user", createUserResponse(userPrincipal));
            response.put("accessToken", accessToken);
            response.put("refreshToken", refreshToken);
            response.put("tokenType", "Bearer");
            response.put("expiresIn", 3600); // 1시간
            response.put("message", "로그인이 완료되었습니다.");
            
            log.info("로그인 성공: {}", email);
            return response;
            
        } catch (AuthenticationException e) {
            log.warn("로그인 실패: {}", email);
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
    }
    
    @Override
    public Map<String, Object> refreshToken(String refreshToken) {
        // 토큰 유효성 검증
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 refresh token입니다.");
        }
        
        // Refresh Token인지 확인
        if (!jwtTokenProvider.isRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("refresh token이 아닙니다.");
        }
        
        // 토큰에서 사용자 정보 추출
        String email = jwtTokenProvider.getEmailFromToken(refreshToken);
        Long userId = jwtTokenProvider.getUserIdFromToken(refreshToken);
        
        // 새로운 Access Token 생성
        String newAccessToken = jwtTokenProvider.generateAccessToken(email, userId);
        
        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", newAccessToken);
        response.put("tokenType", "Bearer");
        response.put("expiresIn", 3600); // 1시간
        response.put("message", "토큰이 갱신되었습니다.");
        
        log.info("토큰 갱신 완료: {}", email);
        return response;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getCurrentUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
        
        Map<String, Object> response = new HashMap<>();
        response.put("user", createUserResponse(user));
        response.put("message", "사용자 정보 조회 완료");
        
        return response;
    }
    
    @Override
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
        
        // 현재 비밀번호 확인
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 올바르지 않습니다.");
        }
        
        // 새 비밀번호 암호화
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        
        // 비밀번호 업데이트 (User 엔티티에 update 메서드가 있다고 가정)
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
        
        log.info("비밀번호 변경 완료: {}", user.getEmail());
    }
    
    /**
     * 사용자 응답 데이터 생성
     */
    private Map<String, Object> createUserResponse(User user) {
        Map<String, Object> userResponse = new HashMap<>();
        userResponse.put("id", user.getId());
        userResponse.put("email", user.getEmail());
        userResponse.put("name", user.getName());
        userResponse.put("nickname", user.getNickname());
        userResponse.put("createdAt", user.getCreatedAt());
        return userResponse;
    }
    
    /**
     * 사용자 응답 데이터 생성 (CustomUserPrincipal 버전)
     */
    private Map<String, Object> createUserResponse(CustomUserPrincipal userPrincipal) {
        Map<String, Object> userResponse = new HashMap<>();
        userResponse.put("id", userPrincipal.getId());
        userResponse.put("email", userPrincipal.getEmail());
        userResponse.put("name", userPrincipal.getName());
        userResponse.put("nickname", userPrincipal.getNickname());
        return userResponse;
    }
}