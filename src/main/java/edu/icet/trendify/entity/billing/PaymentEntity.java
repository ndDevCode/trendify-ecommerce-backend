package edu.icet.trendify.entity.billing;

import edu.icet.trendify.util.converters.PaymentMethodConverter;
import edu.icet.trendify.util.converters.PaymentTypeConverter;
import edu.icet.trendify.util.enums.PaymentMethod;
import edu.icet.trendify.util.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Convert(converter = PaymentTypeConverter.class)
    @Column(nullable = false)
    private PaymentType paymentType;
    @Column(nullable = false)
    @Convert(converter = PaymentMethodConverter.class)
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "payment")
    @ToString.Exclude
    private List<BillingInfoEntity> billingInfoList;
}
