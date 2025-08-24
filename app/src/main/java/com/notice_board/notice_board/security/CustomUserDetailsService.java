package com.notice_board.notice_board.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Task 1.3: 기본 구조만 구현
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 기본 구조만 구현
        throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
    }

    public UserDetails loadUserById(Long userId) {
        // 기본 구조만 구현
        throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId);
    }
}