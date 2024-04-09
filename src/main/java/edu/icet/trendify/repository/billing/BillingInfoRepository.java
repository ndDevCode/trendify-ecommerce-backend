package edu.icet.trendify.repository.billing;

import edu.icet.trendify.entity.billing.BillingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingInfoRepository extends JpaRepository<BillingInfoEntity, Long>, JpaSpecificationExecutor<BillingInfoEntity> {
    Optional<BillingInfoEntity> findByOrder_Id(Long id);
}