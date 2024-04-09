package edu.icet.trendify.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link edu.icet.trendify.entity.product.SubCategoryEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SubCategoryDto(
        Integer id,
        @NotEmpty(message = "Name should not be empty")
        String name,
        List<MainCategoryDto> mainCategoryList
) implements Serializable {
}