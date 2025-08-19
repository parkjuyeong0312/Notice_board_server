package com.notice_board.notice_board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notice_board.notice_board.security.CustomUserPrincipal;
import com.notice_board.notice_board.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    private final AuthService authService;
    
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /*
     * 회원가입
     * POST /api/v1/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, Object> registerData) {
        
        try {
            String email = (String) registerData.get("email");
            String password = (String) registerData.get("password");
            String name = (String) registerData.get("name");
            String nickname = (String) registerData.get("nickname");
            
            Map<String, Object> response = authService.register(email, password, name, nickname);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = Map.of(
                "success", false,
                "message", e.getMessage()
            );
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (Exception e) {
            Map<String, Object> errorResponse = Map.of(
                "success", false,
                "message", "회원가입 중 오류가 발생했습니다."
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /*
     * 로그인
     * POST /api/v1/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> loginData) {
        
        try {
            String email = (String) loginData.get("email");
            String password = (String) loginData.get("password");
            
            Map<String, Object> response = authService.login(email, password);
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = Map.of(
                "success", false,
                "message", e.getMessage()
            );
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (Exception e) {
            Map<String, Object> errorResponse = Map.of(
                "success", false,
                "message", "로그인 중 오류가 발생했습니다."
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /*
     * 로그아웃
     * POST /api/v1/auth/logout
     */
    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        
        //[TODO] 로그아웃 로직 구현
        //[TODO] 토큰 블랙리스트 추가 (선택사항)
        Map<String, Object> response = Map.of(
            "message", "로그아웃 성공 - 구현예정",
            "data", Map.of("loggedOut", true)
        );
        
        return ResponseEntity.ok(response);
    }

    /*
     * 현재 사용자 정보 조회
     * GET /api/v1/auth/me
     */
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser(
            @AuthenticationPrincipal CustomUserPrincipal userPrincipal) {
        
        try {
            if (userPrincipal == null) {
                Map<String, Object> errorResponse = Map.of(
                    "success", false,
                    "message", "인증이 필요합니다."
                );
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
            
            Map<String, Object> response = authService.getCurrentUser(userPrincipal.getId());
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = Map.of(
                "success", false,
                "message", "사용자 정보 조회 중 오류가 발생했습니다."
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /*
     * 토큰 갱신
     * POST /api/v1/auth/refresh
     */
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, Object>> refreshToken(@RequestBody Map<String, Object> refreshData) {
        
        try {
            String refreshToken = (String) refreshData.get("refreshToken");
            
            Map<String, Object> response = authService.refreshToken(refreshToken);
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = Map.of(
                "success", false,
                "message", e.getMessage()
            );
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (Exception e) {
            Map<String, Object> errorResponse = Map.of(
                "success", false,
                "message", "토큰 갱신 중 오류가 발생했습니다."
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /*
     * 비밀번호 변경
     * POST /api/v1/auth/change-password
     */
    @PostMapping("/change-password")
    public ResponseEntity<Map<String, Object>> changePassword(
            @AuthenticationPrincipal CustomUserPrincipal userPrincipal,
            @RequestBody Map<String, Object> passwordData) {
        
        try {
            if (userPrincipal == null) {
                Map<String, Object> errorResponse = Map.of(
                    "success", false,
                    "message", "인증이 필요합니다."
                );
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
            
            String currentPassword = (String) passwordData.get("currentPassword");
            String newPassword = (String) passwordData.get("newPassword");
            
            authService.changePassword(userPrincipal.getId(), currentPassword, newPassword);
            
            Map<String, Object> response = Map.of(
                "success", true,
                "message", "비밀번호가 성공적으로 변경되었습니다."
            );
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = Map.of(
                "success", false,
                "message", e.getMessage()
            );
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (Exception e) {
            Map<String, Object> errorResponse = Map.of(
                "success", false,
                "message", "비밀번호 변경 중 오류가 발생했습니다."
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}