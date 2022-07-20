package com.seung.shopmall.domain.brand;

import com.seung.shopmall.dto.brand.BrandDto;
import com.seung.shopmall.dto.brand.CreateBrandDto;
import com.seung.shopmall.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    @Transactional
    public BrandDto saveBrand(CreateBrandDto createBrandDto) {
        checkBrandDuplicate(createBrandDto);
        Brand brand = createBrandDto.toEntity();
        Brand saveBrand = brandRepository.save(brand);
        return new BrandDto(saveBrand);
    }

    private void checkBrandDuplicate(CreateBrandDto createBrandDto) {
        brandRepository.findBrandByName(createBrandDto.getName())
                .ifPresent(brand -> {
                    throw new DuplicateResourceException("duplicateBrand");
                });
    }

    public List<BrandDto> getBrandList(){
        List<Brand> brandList = brandRepository.findAll();
        return brandList.stream()
                .map(BrandDto::new)
                .collect(Collectors.toList());

    }
}
