package com.seung.shopmall.dto.product;

import com.seung.shopmall.domain.brand.Brand;
import com.seung.shopmall.domain.product.Category;
import com.seung.shopmall.domain.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "브랜드 생성 정보")
@NoArgsConstructor
@Getter @Setter
public class CreateProductDto {

    @Schema(description = "상품 이름")
    @NotBlank(message = "상품 이름이 비어있습니다.")
    private String name;

    @Schema(description = "브랜드 아이디")
    @Min(value = 1, message = "브랜드 아이디의 유효한 값은 1 이상입니다.")
    private Long brandId;
    @Schema(description = "상품 가격")
    @Min(value = 0, message = "상품 가격의 유효한 값은 0 이상입니다.")
    private Long price;
    @Schema(description = "상품 카테고리")
    @NotNull(message = "상품 카테고리가 비어있습니다.")
    private Category category;

    public Product toEntity(Brand brand) {
        return Product.builder()
                .name(this.name)
                .brand(brand)
                .category(this.category)
                .price(this.price)
                .build();
    }
}
