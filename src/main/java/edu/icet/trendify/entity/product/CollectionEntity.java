package edu.icet.trendify.entity.product;

import edu.icet.trendify.entity.product.ProductCollectionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "collection")
public class CollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double discountRate;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "collection")
    @ToString.Exclude
    private List<ProductCollectionEntity> productCollectionList;
}
