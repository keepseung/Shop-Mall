package com.seung.shopmall.domain.product;

import com.seung.shopmall.domain.brand.Brand;
import com.seung.shopmall.dto.product.BrandProductSumDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p " +
            "left join fetch p.brand " +
            "where p.price = (select min(p2.price) from Product p2 where p2.category = :category) and p.category = :category")
    List<Product> findCategoryMinProduct(Category category, Pageable pageable);

    @Query("select p from Product p " +
            "left join fetch p.brand " +
            "where p.price = (select max(p2.price) from Product p2 where p2.category = :category) and p.category = :category")
    List<Product> findCategoryMaxProduct(Category category, Pageable pageable);

    @Query("select " +
            "new com.seung.shopmall.dto.product.BrandProductSumDto(p.brand.name, SUM(p.price)) " +
            "from Product p " +
            "where p.brand = :brand " +
            "group by p.brand")
    BrandProductSumDto findGroupByBrand(Brand brand);
}
