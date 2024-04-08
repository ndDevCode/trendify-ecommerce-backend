package edu.icet.trendify.repository.product;

import edu.icet.trendify.entity.id.ProductCollectionId;
import edu.icet.trendify.entity.product.ProductCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCollectionRepository extends JpaRepository<ProductCollectionEntity, ProductCollectionId>, JpaSpecificationExecutor<ProductCollectionEntity> {
    List<ProductCollectionEntity> findByCollectionId(Integer collectionId);
}