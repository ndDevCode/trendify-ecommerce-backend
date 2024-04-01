package edu.icet.trendify.entity.inventory;

import edu.icet.trendify.entity.id.SubInventoryId;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "sub_inventory")
@IdClass(SubInventoryId.class)
public class SubInventoryEntity {
    @Id
    private Long inventoryId;
    @Id
    private Integer colorId;
    @Id
    private Integer sizeId;

    private Integer quantity;
    private Boolean isSoldOut;

    @ManyToOne
    @MapsId("inventoryId")
    @JoinColumn(name = "inventory")
    private InventoryEntity inventory;

    @ManyToOne
    @MapsId("colorId")
    @JoinColumn(name = "color")
    private ColorEntity color;

    @ManyToOne
    @MapsId("sizeId")
    @JoinColumn(name = "size")
    private SizeEntity size;
}
