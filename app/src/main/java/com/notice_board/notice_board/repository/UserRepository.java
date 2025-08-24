
package com.notice_board.notice_board.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.notice_board.notice_board.entity.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //이메일
    //1.이메일로 사용자 조회
    Optional<User> findByEmail(String email);

    //2.이메일 존재 여부 확인
    boolean existByEmail(String email);
    
    //닉네임
    //1.닉네임으로 사용자 조회
    Optional<User> findByNickName(String nickName);

    //2.닉네임 존재 여부 확인
    boolean existByNickname(String nickname);
}

