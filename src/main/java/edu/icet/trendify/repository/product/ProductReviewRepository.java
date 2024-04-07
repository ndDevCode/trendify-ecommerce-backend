package edu.icet.trendify.repository.product;

import edu.icet.trendify.entity.id.ProductReviewId;
import edu.icet.trendify.entity.product.ProductReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReviewEntity, ProductReviewId>, JpaSpecificationExecutor<ProductReviewEntity> {
    boolean existsByProductIdAndCusId(Long productId, Long cusId);

    List<ProductReviewEntity> findByCusId(Long cusId);

    List<ProductReviewEntity> findByProductId(Long productId);

    List<ProductReviewEntity> findByRating(Short rating);
}