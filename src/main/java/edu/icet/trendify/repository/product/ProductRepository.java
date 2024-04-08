package edu.icet.trendify.repository.product;

import edu.icet.trendify.entity.product.ProductEntity;
import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, JpaSpecificationExecutor<ProductEntity> {
    @Query(value = "SELECT * FROM product INNER JOIN product_review ON product.id = product_review.product WHERE product_review.rating = :rating", nativeQuery = true)
    List<ProductEntity> findByRating(@Param("rating") Short rating);

    List<ProductEntity> findByProductCollectionList_CollectionId(Integer collectionId);
}