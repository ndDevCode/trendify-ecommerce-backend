package edu.icet.trendify.entity.billing;

import edu.icet.trendify.entity.order.OrderEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "billing_info")
public class BillingInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Double totalPrice;
    @Column(nullable = false)
    private Double discount;
    @Column(nullable = false)
    private Double tax;

    @OneToOne(mappedBy = "billingInfo")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;
}
