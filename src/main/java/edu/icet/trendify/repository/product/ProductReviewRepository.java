package edu.icet.trendify.repository.product;

import edu.icet.trendify.entity.id.ProductReviewId;
import edu.icet.trendify.entity.product.ProductReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReviewEntity, ProductReviewId>, JpaSpecificationExecutor<ProductReviewEntity> {
}