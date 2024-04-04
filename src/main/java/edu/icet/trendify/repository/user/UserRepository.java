package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE user SET password = :password WHERE email = :email", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("email") String email);
}