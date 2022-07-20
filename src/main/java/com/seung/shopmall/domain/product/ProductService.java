package com.seung.shopmall.domain.product;

import com.seung.shopmall.domain.brand.Brand;
import com.seung.shopmall.domain.brand.BrandRepository;
import com.seung.shopmall.dto.product.*;
import com.seung.shopmall.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.seung.shopmall.domain.product.Category.*;
import static com.seung.shopmall.domain.product.Category.ACCESSORY;
import static java.util.stream.Collectors.reducing;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    public ProductDto saveProduct(CreateProductDto createProductDto) {
        Brand brand = findBrand(createProductDto.getBrandId());
        Product saveProduct = productRepository.save(createProductDto.toEntity(brand));
        return new ProductDto(saveProduct);
    }

    private Brand findBrand(Long brandId) {
        return brandRepository.findById(brandId).orElseThrow(() -> {
            throw new NotFoundException("brandNotFound");
        });
    }

    private Brand findBrand(String brandName) {
        return brandRepository.findBrandByName(brandName).orElseThrow(() -> {
            throw new NotFoundException("brandNotFound");
        });
    }

    public List<ProductDto> getProductList() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    public ProductDto updateProduct(Long id, UpdateProductDto updateProductDto) {
        Product product = findProduct(id);
        Long brandId = updateProductDto.getBrandId();
        if (brandId != null && brandId>0){
            Brand findBrand = findBrand(brandId);
            product.updateBranch(findBrand);
        }
        product.update(updateProductDto);
        return new ProductDto(product);
    }

    private Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("productNotFound");
        });
    }

    public void deleteProduct(Long id) {
        Product product = findProduct(id);
        productRepository.delete(product);
    }

    public CategoryMinPriceDto getCategoryMinPriceProductList() {

        List<Category> categories = List.of(TOP, COAT,PANTS,SNEAKERS, BAG, HAT, SOCKS, ACCESSORY);
        List<Product> minProductList = new ArrayList<>();
        for (Category category : categories) {
            List<Product> products = productRepository.findCategoryMinProduct(category, PageRequest.of(0, 1));
            minProductList.add(products.get(0));
        }
        Long priceSum = minProductList.stream()
                .map(Product::getPrice)
                .reduce(0L, Long::sum);
        List<ProductDto> productList = minProductList.stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());

        return new CategoryMinPriceDto(priceSum, productList);

    }

    public BrandProductSumDto getBrandMinPrice(String brandName) {
        Brand brand = findBrand(brandName);
        return productRepository.findGroupByBrand(brand);
    }

    public CategoryPriceDto getCategoryPrice(Category category) {
        List<Product> categoryMinProducts = productRepository.findCategoryMinProduct(category, PageRequest.of(0, 1));
        List<Product> categoryMaxProducts = productRepository.findCategoryMaxProduct(category, PageRequest.of(0, 1));
        return CategoryPriceDto.builder()
                .maxProduct(new ProductDto(categoryMaxProducts.get(0)))
                .minProduct(new ProductDto(categoryMinProducts.get(0)))
                .build();
    }
}
