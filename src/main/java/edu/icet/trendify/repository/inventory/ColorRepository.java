package edu.icet.trendify.repository.inventory;

import edu.icet.trendify.entity.inventory.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Integer>, JpaSpecificationExecutor<ColorEntity> {
}