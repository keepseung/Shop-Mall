package com.seung.shopmall.domain.brand;

import com.seung.shopmall.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name; // 브랜드 이름

    @OneToMany(mappedBy = "brand")
    private List<Product> productList = new ArrayList<>();

    @Builder
    public Brand(String name) {
        this.name = name;
    }
}
