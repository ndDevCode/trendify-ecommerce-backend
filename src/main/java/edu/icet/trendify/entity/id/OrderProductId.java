package edu.icet.trendify.entity.id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderProductId implements Serializable {
    private Long orderId;
    private Integer productId;
}
