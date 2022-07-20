package com.seung.shopmall.domain.brand;

import com.seung.shopmall.dto.brand.BrandDto;
import com.seung.shopmall.dto.brand.CreateBrandDto;
import com.seung.shopmall.response.ListResponse;
import com.seung.shopmall.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.seung.shopmall.response.SingleResponse.getSingleResponse;

@RequiredArgsConstructor
@RequestMapping("/api/brand")
@RestController
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = "브랜드 생성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SingleResponse<BrandDto> saveBrand(@Valid @RequestBody CreateBrandDto createBrandDto){
        return getSingleResponse(brandService.saveBrand(createBrandDto));
    }

    @Operation(summary = "브랜드 리스트 조회")
    @GetMapping
    public ListResponse<BrandDto> getBrandList(){
        return ListResponse.getListResponse(brandService.getBrandList());
    }
}
