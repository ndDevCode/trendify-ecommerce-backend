package edu.icet.trendify.entity.inventory;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "inventory")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long totalQuantity;
    @Column(nullable = false)
    private Double totalPrice;
    @Column(nullable = false)
    private Double unitPrice;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Integer reorderLevel;
    @Column(nullable = false)
    private Boolean isReleased;
    private String remarks;

    @OneToOne(mappedBy = "inventory")
    private SubInventoryEntity subInventory;
}
