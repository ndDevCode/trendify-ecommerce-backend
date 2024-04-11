package edu.icet.trendify.entity.order;

import edu.icet.trendify.entity.billing.BillingInfoEntity;
import edu.icet.trendify.entity.user.CustomerEntity;
import edu.icet.trendify.util.constant.OrderStatusConverter;
import edu.icet.trendify.util.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.mapstruct.Named;

import java.time.LocalDateTime;
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
    private LocalDateTime orderPlacedAt;

    @Column(nullable = false)
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderStatus;


    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private CustomerEntity customer;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(nullable = false)
    private BillingInfoEntity billingInfo;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<OrderProductEntity> orderProducts;
}
