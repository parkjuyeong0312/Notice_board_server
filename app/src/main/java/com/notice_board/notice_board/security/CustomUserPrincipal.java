package com.notice_board.notice_board.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.notice_board.notice_board.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomUserPrincipal implements UserDetails {
    
    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private Collection<? extends GrantedAuthority> authorities;
    
    public static CustomUserPrincipal create(User user) {
        return new CustomUserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getNickname(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public String getUsername() {
        return email;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}