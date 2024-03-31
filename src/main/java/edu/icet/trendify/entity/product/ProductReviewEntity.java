package edu.icet.trendify.entity.product;

import edu.icet.trendify.entity.id.ProductReviewId;
import edu.icet.trendify.entity.user.CustomerEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "product_review")
@IdClass(ProductReviewId.class)
public class ProductReviewEntity {
    @Id
    private Long cusId;
    @Id
    private Long productId;

    private String review;
    private Short rating;

    @ManyToOne
    @MapsId("cusId")
    @JoinColumn(name = "customer")
    private CustomerEntity customer;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product")
    private ProductEntity product;
}
