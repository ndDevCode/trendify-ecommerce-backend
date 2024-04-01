package edu.icet.trendify.repository.inventory;

import edu.icet.trendify.entity.id.SubInventoryId;
import edu.icet.trendify.entity.inventory.SubInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubInventoryRepository extends JpaRepository<SubInventoryEntity, SubInventoryId>, JpaSpecificationExecutor<SubInventoryEntity> {
}