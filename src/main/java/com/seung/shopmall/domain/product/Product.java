package com.seung.shopmall.domain.product;

import com.seung.shopmall.domain.brand.Brand;
import com.seung.shopmall.dto.product.UpdateProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(indexes = @Index(name = "idx_product_brand_category",columnList = "brand_id, category"))
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; // 상품 이름

    @JoinColumn(name = "brand_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand; // 브랜드

    @Enumerated(EnumType.STRING)
    private Category category; // 카테고리

    private Long price; // 가격

    @Builder
    public Product(String name, Brand brand, Category category, Long price) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
    }

    public void updateBranch(Brand brand){
        this.brand = brand;
    }

    public void update(UpdateProductDto updateProductDto){
        if (Strings.isNotBlank(updateProductDto.getName())){
            this.name = updateProductDto.getName();
        }
        if (updateProductDto.getCategory() != null){
            this.category = updateProductDto.getCategory();
        }
        if (updateProductDto.getPrice() != null && updateProductDto.getPrice() >=0){
            this.price = updateProductDto.getPrice();
        }
    }
}
