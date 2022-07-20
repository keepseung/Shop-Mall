package com.seung.shopmall.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter @Setter
@NoArgsConstructor
public class BrandProductSumDto {
    private String brandName;
    private String price;

    @Builder
    public BrandProductSumDto(String brandName, Long price) {
        this.brandName = brandName;
        DecimalFormat commaFormat = new DecimalFormat("#,###");
        this.price = commaFormat.format(price);
    }
}
