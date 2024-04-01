package edu.icet.trendify.repository.product;

import edu.icet.trendify.entity.product.MainCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategoryEntity, Integer>, JpaSpecificationExecutor<MainCategoryEntity> {
}