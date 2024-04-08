package edu.icet.trendify.repository.product;

import edu.icet.trendify.entity.product.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Integer>, JpaSpecificationExecutor<CollectionEntity> {
    List<CollectionEntity> findByProductCollectionList_ProductId(Integer productId);
}