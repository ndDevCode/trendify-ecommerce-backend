package edu.icet.trendify.repository.product;

import edu.icet.trendify.entity.product.MainCategoryEntity;
import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategoryEntity, Integer>, JpaSpecificationExecutor<MainCategoryEntity> {
    boolean existsByName(String name);

    @Query(value =
            "SELECT * FROM main_category " +
            "INNER JOIN main_sub_category " +
            "ON main_category.id = main_sub_category.main_category_id " +
            "WHERE main_sub_category.sub_category_id = :id", nativeQuery = true)
    List<MainCategoryEntity> findBySubCategoryId(@Param("id") Integer id);

    @Modifying
    @Query(value =
            "DELETE FROM main_sub_category " +
            "WHERE sub_category_id = :sub_id " +
            "AND main_category_id = :main_id", nativeQuery = true)
    void deleteBySubCategoryId(@Param("sub_id") Integer subCategoryId,@Param("main_id") Integer mainCategoryId);

    @Query(value =
            "SELECT * FROM main_sub_category " +
            "WHERE main_category_id = :main_id " +
            "AND sub_category_id = :sub_id >= 1", nativeQuery = true)
    boolean existsBySubAndMainCategoryIds(@Param("sub_id") Integer subCategoryId,@Param("main_id") Integer mainCategoryId);

    @Modifying
    @Query(value = "INSERT INTO main_sub_category (main_category_id, sub_category_id) VALUES (:main_id, :sub_id)", nativeQuery = true)
    void saveMainSubCategory(@Param("sub_id") Integer subCategoryId,@Param("main_id") Integer mainCategoryId);
}