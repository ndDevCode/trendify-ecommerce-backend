package edu.icet.trendify.entity.id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductCollectionId implements Serializable {
    private Integer productId;
    private Integer collectionId;
}
