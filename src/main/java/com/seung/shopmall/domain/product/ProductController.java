package com.seung.shopmall.domain.product;

import com.seung.shopmall.dto.brand.BrandDto;
import com.seung.shopmall.dto.brand.CreateBrandDto;
import com.seung.shopmall.dto.product.*;
import com.seung.shopmall.exception.BadRequestException;
import com.seung.shopmall.response.ListResponse;
import com.seung.shopmall.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.seung.shopmall.response.ListResponse.*;
import static com.seung.shopmall.response.SingleResponse.getSingleResponse;

@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
public class ProductController {
    private final ProductService productService;

    /**
     * 1. 모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할 때 최저가 조회 API
     */
    @Operation(summary = "모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할 때 최저가 조회 API")
    @GetMapping("/category-min-price")
    public SingleResponse<CategoryMinPriceDto> getCategoryMinPriceProductList() {
        return getSingleResponse(productService.getCategoryMinPriceProductList());
    }

    /**
     * 2. 한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회 API
     */
    @Operation(summary = "한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회 API")
    @GetMapping("/brand-min-price")
    public SingleResponse<BrandProductSumDto> getBrandMinPrice(@RequestParam String brandName) {
        if (Strings.isBlank(brandName)){
            throw new BadRequestException("requestValidationError");
        }
        return getSingleResponse(productService.getBrandMinPrice(brandName));
    }

    /**
     * 3. 각 카테고리 이름으로 최소, 최대 가격 조회 API
     */
    @Operation(summary = "각 카테고리 이름으로 최소, 최대 가격 조회 API")
    @GetMapping("/category/price")
    public SingleResponse<CategoryPriceDto> getCategoryPrice(@RequestParam String category) {
        if (Strings.isBlank(category)){
            throw new BadRequestException("requestValidationError");
        }
        Category categoryByKoName = Category.findByKoName(category);
        if (categoryByKoName == null){
            throw new BadRequestException("requestValidationError");
        }
        return getSingleResponse(productService.getCategoryPrice(categoryByKoName));
    }


    /**
     * 브랜드 상품 가격 추가 / 업데이트 / 삭제 API
     */
    @Operation(summary = "상품 추가")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SingleResponse<ProductDto> saveProduct(@Valid @RequestBody CreateProductDto createProductDto) {
        return getSingleResponse(productService.saveProduct(createProductDto));
    }

    @Operation(summary = "상품 리스트 조회")
    @GetMapping
    public ListResponse<ProductDto> getProductList() {
        return getListResponse(productService.getProductList());
    }

    @Operation(summary = "상품 변경")
    @PatchMapping("/{id}")
    public SingleResponse<ProductDto> updateProduct(@PathVariable Long id,
                                                    @Valid @RequestBody UpdateProductDto updateProductDto) {
        return getSingleResponse(productService.updateProduct(id, updateProductDto));
    }

    @Operation(summary = "상품 삭제")
    @DeleteMapping("/{id}")
    public SingleResponse<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return getSingleResponse(null);
    }
}
