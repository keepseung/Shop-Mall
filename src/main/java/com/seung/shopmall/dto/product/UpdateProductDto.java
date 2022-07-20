package com.seung.shopmall.dto.product;

import com.seung.shopmall.domain.brand.Brand;
import com.seung.shopmall.domain.product.Category;
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
public class UpdateProductDto {

    @Schema(description = "상품 이름")
    private String name;
    @Schema(description = "브랜드 아이디")
    private Long brandId;
    @Schema(description = "상품 가격")
    private Long price;
    @Schema(description = "상품 카테고리")
    private Category category;
}
