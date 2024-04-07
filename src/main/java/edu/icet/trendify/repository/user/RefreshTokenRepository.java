package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.user.RefreshTokenEntity;
import edu.icet.trendify.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, String> {
    @Modifying
    @Query(value = "DELETE from refresh_token where user_id = :id", nativeQuery = true)
    void deleteByUser(@Param("id") Long id);
}