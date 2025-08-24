package com.notice_board.notice_board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.notice_board.notice_board.entity.User;
import com.notice_board.notice_board.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    //비밀번호 인코더
    @Autowired
    private PasswordEncoder passwordEncoder;


    // ===== CREATE =====
    // 회원가입
    // resisterUser()구조
    // 1. 이메일 중복 확인
    // 2. 닉네임 중복 확인
    // 3. 비밀번호 암호화
    // 4. 사용자 생성
    // 5. 사용자 저장
    // 6. 응답 데이터 생성
    
    @Override
    public Map<String, Object> registerUser(String email, String password, String name, String nickname){
        Map<String, Object> response = new HashMap<>();

        try{
            // 이메일 중복 확인
            if(userRepository.existByEmail(email)){
                response.put("success", false);
                response.put("message", "이미 존재하는 이메일입니다.");
                response.put("data", null);
                return response;
            }

            // 닉네임 중복 확인
            if(userRepository.existByNickname(nickname)){
                response.put("success", false);
                response.put("message", "이미 존재하는 닉네임입니다.");
                response.put("data", null);
                return response;
            }
            
            // 비밀번호 암호화
            String encodedPassword = passwordEncoder.encode(password);
            
            // 사용자 생성
            //1. 사용자 객체 생성
            User user = new User(email, encodedPassword, name , nickname);
            
            //2. 사용자 저장
            // save()를 호출하면, user 객체가 DB에 저장되고, 저장된 엔티티(=User)가 반환됨
            // 만약 user의 id가 null이면 새로 insert, id가 있으면 update 동작
            User savedUser = userRepository.save(user);

            //3.응답 데이터 생성
            Map<String, Object> Userdata = new HashMap<>();
            Userdata.put("id", savedUser.getId());
            Userdata.put("email", savedUser.getEmail());
            Userdata.put("name", savedUser.getName());
            Userdata.put("nickname", savedUser.getNickname());
            Userdata.put("createdAt", savedUser.getCreatedAt());
            Userdata.put("updatedAt", savedUser.getUpdatedAt());

            response.put("success", true);
            response.put("message", "사용자 등록 성공");
            response.put("data", Userdata);
        } catch(Exception e){
            response.put("success", false);
            response.put("message", "사용자 등록 중 오류가 발생했습니다.");
            response.put("data", null);
        }
        return response;
    }

    // ===== READ =====
    // ID로 사용자 조회
    // 1. 사용자 존재 여부 확인
    // 2. 사용자 정보 반환
    // 3. 예외 처리
    // 4. 응답 데이터 생성
    // 5. 응답 데이터 반환
    @Override
    public Optional<Map<String, Object>> findById(Long userId){
        try{
            Optional<User> userOpt = userRepository.findById(userId);
            // 사용자 존재 여부 확인
            if(userOpt.isPresent()){
                User user = userOpt.get();
                Map<String, Object> userData = new HashMap<>();
                userData.put("id", user.getId());
                userData.put("email", user.getEmail());
                userData.put("name", user.getName());
                userData.put("nickname", user.getNickname());
                userData.put("createdAt", user.getCreatedAt());
                userData.put("updatedAt", user.getUpdatedAt());

                return Optional.of(userData);
            }
            return Optional.empty();
        } catch(Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Map<String,Object>> findByEmail(String email){
        try{
            Optional<User> userOpt = userRepository.findByEmail(email);
            if(userOpt.isPresent()){
                User user = userOpt.get();
                Map<String, Object> userData = new HashMap<>();
                userData.put("id", user.getId());
                userData.put("email", user.getEmail());
                userData.put("name", user.getName());
                userData.put("nickname", user.getNickname());
                userData.put("createdAt", user.getCreatedAt());
                userData.put("updatedAt", user.getUpdatedAt());

                return Optional.of(userData);
            }
            return Optional.empty();
        } catch(Exception e){
            return Optional.empty();
        }
    }

    // UPDATE
    // UpdateUser
    @Override
    public Map<String,Object> updateUser(Long userId, String name, String nickName){
        Map<String, Object> response = new HashMap<>();

        try{
            Optional<User> userOpt = userRepository.findById(userId);
            
            //사용자를 찾을 수 없다면
            if(!userOpt.isPresent()){
                response.put("success", false);
                response.put("message", "사용자를 찾을 수 없습니다.");
                response.put("data", null);
                return response;
            }
            //사용자를 찾았다면
            User user = userOpt.get();

            //닉네임 중복 확인 (자신의 닉네임은 제외)
            //중복 닉네임이 있고, 자신의 닉네임과 겹친다면
            if(!user.getNickname().equals(nickName) && userRepository.existByNickname(nickName)){
                response.put("success", false);
                response.put("message", "이미 존재하는 닉네임입니다.");
                response.put("data", null);
                return response;
            }

            //사용자 정보 업데이트
            user.setName(name);
            user.setNickname(nickName);
            user.setUpdatedAt(LocalDateTime.now());

            User updatedUser = userRepository.save(user);

            //응답데이터 설정
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", updatedUser.getId());
            userData.put("email", updatedUser.getEmail());
            userData.put("name", updatedUser.getName());
            userData.put("nickname", updatedUser.getNickname());
            userData.put("createdAt", updatedUser.getCreatedAt());
            userData.put("updatedAt", updatedUser.getUpdatedAt());
            
            response.put("success", true);
            response.put("message", "사용자 정보 업데이트 성공");
            response.put("data", userData);
        } catch(Exception e){
            response.put("success", false);
            response.put("message", "사용자 정보 업데이트 중 오류가 발생했습니다.");
            response.put("data", null);
        }
        return response;
    }

    // 비밀번호 변경
    //궁금증 -> 왜 비밀번호 변경 성공시 반환할 필요 없음?
    @Override
    public void changePassword(Long userId, String currentPassword, String newPassword){
        
        try{
            //사용자 존재 여부를 확인
            Optional<User> userOpt = userRepository.findById(userId);
            if(!userOpt.isPresent()){
                //[Todo] 커스텀 예외 처리 필요
                throw new RuntimeException("사용자를 찾을 수 없습니다.");
            }

            //사용자가 존재한다면
            User user = userOpt.get();

            //현재 비밀번호와 일치하지 않다면
            if(!passwordEncoder.matches(currentPassword, user.getPassword())){
                throw new RuntimeException("현재 비밀번호가 일치하지 않습니다.");
            }

            //새로운 비밀번호 암호화 및 저장
            String encodedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedPassword);
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);
        } catch(Exception e){
            throw new RuntimeException("비밀번호 변경 중 오류가 발생했습니다."+ e.getMessage());
        }
    }
        @Override
        public void deleteUser(Long userId){
            try{
                Optional<User> userOpt = userRepository.findById(userId);
                if(!userOpt.isPresent()){
                    throw new RuntimeException("사용자를 찾을 수 없습니다.");
                }

                userRepository.deleteById(userId);
            } catch(Exception e){   
                throw new RuntimeException("사용자 삭제 중 오류가 발생했습니다."+ e.getMessage());
            }
        }
    
    
}
    




