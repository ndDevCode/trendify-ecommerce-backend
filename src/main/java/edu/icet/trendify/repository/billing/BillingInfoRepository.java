package edu.icet.trendify.repository.billing;

import edu.icet.trendify.entity.billing.BillingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingInfoRepository extends JpaRepository<BillingInfoEntity, Long>, JpaSpecificationExecutor<BillingInfoEntity> {
}