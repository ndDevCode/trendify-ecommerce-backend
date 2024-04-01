package edu.icet.trendify.repository.product;

import edu.icet.trendify.entity.product.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Integer>, JpaSpecificationExecutor<SubCategoryEntity> {
}