package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.user.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long>, JpaSpecificationExecutor<CartEntity> {
}