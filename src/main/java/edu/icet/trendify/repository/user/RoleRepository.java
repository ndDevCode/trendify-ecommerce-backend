package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.user.RoleEntity;
import edu.icet.trendify.util.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRole(Role role);
}