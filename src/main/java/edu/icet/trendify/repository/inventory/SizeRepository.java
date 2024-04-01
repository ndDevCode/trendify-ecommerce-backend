package edu.icet.trendify.repository.inventory;

import edu.icet.trendify.entity.inventory.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, Integer>, JpaSpecificationExecutor<SizeEntity> {
}