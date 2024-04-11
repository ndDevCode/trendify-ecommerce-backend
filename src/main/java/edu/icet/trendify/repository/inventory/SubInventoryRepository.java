package edu.icet.trendify.repository.inventory;

import edu.icet.trendify.entity.id.SubInventoryId;
import edu.icet.trendify.entity.inventory.SubInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubInventoryRepository extends JpaRepository<SubInventoryEntity, SubInventoryId>, JpaSpecificationExecutor<SubInventoryEntity> {

    List<SubInventoryEntity> findByInventory_Id(Long id);
}