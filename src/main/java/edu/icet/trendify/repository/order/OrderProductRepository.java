package edu.icet.trendify.repository.order;

import edu.icet.trendify.entity.id.OrderProductId;
import edu.icet.trendify.entity.order.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProductEntity, OrderProductId>, JpaSpecificationExecutor<OrderProductEntity> {
}