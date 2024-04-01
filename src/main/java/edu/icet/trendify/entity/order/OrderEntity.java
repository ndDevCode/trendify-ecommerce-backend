package edu.icet.trendify.entity.order;

import edu.icet.trendify.entity.billing.BillingInfoEntity;
import edu.icet.trendify.entity.user.CustomerEntity;
import edu.icet.trendify.util.enums.OrderStatus;
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
    @Column(nullable = false)
    private String contact;
    @Column(nullable = false)
    private Long addressId;
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private CustomerEntity customer;

    @OneToOne
    @JoinColumn(nullable = false)
    private BillingInfoEntity billingInfo;

    @OneToMany(mappedBy = "order")
    @JoinColumn(nullable = false)
    private List<OrderProductEntity> orderProducts;
}
