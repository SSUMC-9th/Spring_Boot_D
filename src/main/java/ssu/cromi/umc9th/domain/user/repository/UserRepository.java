package ssu.cromi.umc9th.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ssu.cromi.umc9th.domain.user.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT UserProfileDto(u.id, u.nickname, u.email, u.phone, u.userPoint) " + "FROM User u WHERE u.id = :id")
    List<User> findbyId(@Param("id") Long id);
}
