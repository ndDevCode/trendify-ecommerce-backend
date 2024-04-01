package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.id.CustomerAddressId;
import edu.icet.trendify.entity.user.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, CustomerAddressId>, JpaSpecificationExecutor<CustomerAddressEntity> {
}