package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.id.CartProductId;
import edu.icet.trendify.entity.user.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, CartProductId>, JpaSpecificationExecutor<CartProductEntity> {
}