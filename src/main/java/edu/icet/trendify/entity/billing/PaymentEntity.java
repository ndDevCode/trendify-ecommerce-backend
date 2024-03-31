package edu.icet.trendify.entity.billing;

import edu.icet.trendify.util.enums.PaymentMethod;
import edu.icet.trendify.util.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private PaymentType paymentType;
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "payment")
    private List<BillingInfoEntity> billingInfoList;
}
