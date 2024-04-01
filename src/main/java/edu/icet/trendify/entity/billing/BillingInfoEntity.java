package edu.icet.trendify.entity.billing;

import edu.icet.trendify.entity.order.OrderEntity;
import edu.icet.trendify.util.converter.PaymentMethodConverter;
import edu.icet.trendify.util.converter.PaymentTypeConverter;
import edu.icet.trendify.util.enums.PaymentMethod;
import edu.icet.trendify.util.enums.PaymentType;
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

    @Column(nullable = false)
    @Convert(converter = PaymentTypeConverter.class)
    private PaymentType paymentType;
    @Column(nullable = false)
    @Convert(converter = PaymentMethodConverter.class)
    private PaymentMethod paymentMethod;

    @OneToOne(mappedBy = "billingInfo")
    private OrderEntity order;
}
