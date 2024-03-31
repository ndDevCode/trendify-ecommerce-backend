package edu.icet.trendify.entity.order;

import edu.icet.trendify.entity.billing.BillingInfoEntity;
import edu.icet.trendify.entity.user.CustomerEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contact;
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToOne
    private BillingInfoEntity billingInfo;

    @OneToMany(mappedBy = "order")
    private List<OrderProductEntity> orderProducts;
}
