package edu.icet.trendify.entity.user;

import edu.icet.trendify.entity.id.CustomerAddressId;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "customer_address")
@IdClass(CustomerAddressId.class)
public class CustomerAddressEntity {
    @Id
    private Long cusId;
    @Id
    private Long addressId;

    @Column(nullable = false)
    private String houseNo;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String province;
    @Column(nullable = false)
    private String postalCode;
    @Column(nullable = false)
    private Boolean isDefault;

    @ManyToOne
    @MapsId("cusId")
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
