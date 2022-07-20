package com.seung.shopmall.dto.brand;

import com.seung.shopmall.domain.brand.Brand;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class BrandDto {
    private Long id;
    private String name;

    public BrandDto(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
    }
}
