package edu.icet.trendify.entity.id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SubInventoryId implements Serializable {
    private Long inventoryId;
    private Integer colorId;
    private Integer sizeId;
}
