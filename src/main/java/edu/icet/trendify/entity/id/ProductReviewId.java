package edu.icet.trendify.entity.id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductReviewId implements Serializable {
    private Long cusId;
    private Long productId;
}
