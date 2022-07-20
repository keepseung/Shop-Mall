package com.seung.shopmall.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryPriceDto {
    private ProductDto minProduct;
    private ProductDto maxProduct;

    @Builder
    public CategoryPriceDto(ProductDto minProduct, ProductDto maxProduct) {
        this.minProduct = minProduct;
        this.maxProduct = maxProduct;
    }
}
