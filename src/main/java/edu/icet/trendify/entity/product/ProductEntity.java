package edu.icet.trendify.entity.product;

import edu.icet.trendify.entity.user.CartProductEntity;
import edu.icet.trendify.entity.order.OrderProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    private String image;
    private Double discount;
    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<CartProductEntity> cartProductList;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<OrderProductEntity> orderProductList;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<ProductReviewEntity> reviewList;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<ProductCollectionEntity> productCollectionList;
}
