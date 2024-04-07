package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.user.JwtTokenEntity;
import edu.icet.trendify.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository<JwtTokenEntity, String> {

    @Modifying
    @Query(value = "DELETE from jwt_token where user_id = :userId", nativeQuery = true)
    void deleteByUser(@Param("userId") Long userId);

    boolean existsByTokenAndIsExpiredFalse(String token);

    Optional<JwtTokenEntity> findByToken(String token);
}