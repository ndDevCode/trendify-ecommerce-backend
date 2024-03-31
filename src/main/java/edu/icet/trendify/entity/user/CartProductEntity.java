package edu.icet.trendify.entity.user;

import edu.icet.trendify.entity.id.CartProductId;
import edu.icet.trendify.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "cart_product")
@IdClass(CartProductId.class)
public class CartProductEntity {
    @Id
    private Long cartId;
    @Id
    private Long productId;

    private Integer quantity;
    private Integer colorId;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart")
    private CartEntity cart;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product")
    private ProductEntity product;
}
