package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.user.AdminEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long>, JpaSpecificationExecutor<AdminEntity> {
    boolean existsById(@NonNull Long id);
    Optional<AdminEntity> findByContact(String contact);
}