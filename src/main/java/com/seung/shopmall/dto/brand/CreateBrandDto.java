package com.seung.shopmall.dto.brand;

import com.seung.shopmall.domain.brand.Brand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Schema(description = "브랜드 생성 정보")
@NoArgsConstructor
@Getter @Setter
public class CreateBrandDto {

    @Schema(description = "브랜드 이름")
    @NotBlank(message = "브랜드 이름이 비어있습니다.")
    private String name;

    public Brand toEntity() {
        return Brand.builder()
                .name(this.name)
                .build();
    }
}
