package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.user.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {
    PasswordResetTokenEntity findByToken(String token);
    PasswordResetTokenEntity findByUserId(Long userId);
}