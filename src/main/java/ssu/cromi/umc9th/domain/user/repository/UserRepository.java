package ssu.cromi.umc9th.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ssu.cromi.umc9th.domain.user.entity.User;
import ssu.cromi.umc9th.domain.user.dto.UserProfileDto;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일 조회 시 fetch join 추가
    @Query("""
        SELECT u
        FROM User u
        WHERE u.email = :email
        """)
    Optional<User> findByEmail(@Param("email") String email);

    @Query("""
        SELECT new ssu.cromi.umc9th.domain.user.dto.UserProfileDto(
            u.userId, u.nickname, u.email, u.phone, u.userPoint
        )
        FROM User u
        WHERE u.userId = :userId
        """)
    UserProfileDto findbyId(@Param("userId") Long userId);
}
