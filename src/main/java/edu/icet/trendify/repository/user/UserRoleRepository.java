package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.id.UserRoleId;
import edu.icet.trendify.entity.user.UserRoleEntity;
import edu.icet.trendify.util.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleId> {
    @Modifying
    @Query(value = "INSERT INTO user_role (user, role) VALUES (:userId, :roleId)", nativeQuery = true)
    void saveUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Modifying
    @Query(value = "DELETE FROM user_role WHERE user = :userId AND role = :roleId", nativeQuery = true)
    void deleteUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Modifying
    @Query(value = "SELECT role.role FROM role INNER JOIN user_role ON user_role.role = role.id WHERE user_role.user = :userId", nativeQuery = true)
    List<Role> getRoleByUserId(Long userId);

    boolean existsByUserIdAndRoleId(Long userId, Long roleId);
}