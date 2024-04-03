package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.user.AdminEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long>, JpaSpecificationExecutor<AdminEntity> {
    boolean existsById(@NonNull Long id);
    Optional<AdminEntity> findByContact(String contact);

    @Query(value = "SELECT admin.id,admin.first_name,admin.last_name,admin.contact,user.email,user.is_active  FROM admin INNER JOIN user ON admin.user_id = user.id", nativeQuery = true)
    List<Object> findAllAdmins();
}