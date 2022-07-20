package com.seung.shopmall.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CategoryMinPriceDto {
    private String totalPrice;
    private List<ProductDto> productList;

    @Builder
    public CategoryMinPriceDto(Long totalPrice, List<ProductDto> productList) {
        DecimalFormat commaFormat = new DecimalFormat("#,###");
        this.totalPrice = commaFormat.format(totalPrice);
        this.productList = productList;
    }
}
