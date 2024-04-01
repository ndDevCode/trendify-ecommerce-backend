package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.user.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long>, JpaSpecificationExecutor<AdminEntity> {
}