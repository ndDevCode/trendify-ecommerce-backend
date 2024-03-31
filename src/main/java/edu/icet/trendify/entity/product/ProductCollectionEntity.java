package edu.icet.trendify.entity.product;

import edu.icet.trendify.entity.id.ProductCollectionId;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "product_collection")
@IdClass(ProductCollectionId.class)
public class ProductCollectionEntity {
    @Id
    private Integer productId;
    @Id
    private Integer collectionId;

    @ManyToOne
    @MapsId("collectionId")
    @JoinColumn(name = "collection")
    private CollectionEntity collection;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product")
    private ProductEntity product;
}
