package com.seung.shopmall.dto.product;

import com.seung.shopmall.domain.brand.Brand;
import com.seung.shopmall.domain.product.Category;
import com.seung.shopmall.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter @Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String brandName;
    private String price;
    private String category;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.brandName = product.getBrand().getName();
        DecimalFormat commaFormat = new DecimalFormat("#,###");
        this.price = commaFormat.format(product.getPrice());
        this.category = product.getCategory().getKoName();
    }
}
