package edu.icet.trendify.entity.order;

import edu.icet.trendify.entity.id.OrderProductId;
import edu.icet.trendify.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "order_product")
@IdClass(OrderProductId.class)
public class OrderProductEntity {
    @Id
    private Long orderId;
    @Id
    private Integer productId;

    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private String colorId;
    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orders",nullable = false)
    private OrderEntity order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product",nullable = false)
    private ProductEntity product;
}
