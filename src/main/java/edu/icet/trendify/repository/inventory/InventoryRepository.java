package edu.icet.trendify.repository.inventory;

import edu.icet.trendify.entity.inventory.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long>, JpaSpecificationExecutor<InventoryEntity> {
}