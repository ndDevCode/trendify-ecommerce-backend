package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.id.CartProductId;
import edu.icet.trendify.entity.user.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, CartProductId>, JpaSpecificationExecutor<CartProductEntity> {
    @Modifying
    @Query(value = "DELETE FROM cart_product WHERE cart = :id", nativeQuery = true)
    void deleteAllByCartId(@Param("id") Long id);

    @Query(value = "SELECT * FROM cart_product WHERE cart = :id", nativeQuery = true)
    List<CartProductEntity> findAllByCartId(@Param("id") Long id);
}