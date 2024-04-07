package edu.icet.trendify.entity.user;

import edu.icet.trendify.entity.id.ContactId;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "contact")
@IdClass(ContactId.class)
public class ContactEntity {
    @Id
    private String contact;
    @Id
    private Long cusId;

    @ManyToOne
    @MapsId("cusId")
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
