package edu.icet.trendify.repository.product;

import edu.icet.trendify.entity.product.SubCategoryEntity;
import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Integer>, JpaSpecificationExecutor<SubCategoryEntity> {
    boolean existsByName(String name);

    @Query(value =
            "SELECT * FROM sub_category " +
            "INNER JOIN main_sub_category " +
            "ON sub_category.id = main_sub_category.sub_category_id " +
            "WHERE main_sub_category.main_category_id = :id", nativeQuery = true)
    List<SubCategoryEntity> findByMainCategoryId(@Param("id") Integer id);
}