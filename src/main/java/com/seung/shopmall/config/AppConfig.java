package com.seung.shopmall.config;

import com.seung.shopmall.domain.brand.Brand;
import com.seung.shopmall.domain.brand.BrandRepository;
import com.seung.shopmall.domain.product.Category;
import com.seung.shopmall.domain.product.Product;
import com.seung.shopmall.domain.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

import static com.seung.shopmall.domain.product.Category.*;

@Configuration
public class AppConfig {

    // 초기 샘플 데이터 저장하기
    @Bean
    CommandLineRunner run(BrandRepository brandRepository, ProductRepository productRepository){
        return args -> {
            // 브랜드 저장
            String[] brandNameList = {"A", "B", "C", "D", "E", "F","G", "H", "I"};
            ArrayList<Brand> brands = new ArrayList<>();
            for (String brandName : brandNameList) {
                brands.add(Brand.builder()
                        .name(brandName)
                        .build());
            }
            brandRepository.saveAll(brands);

            long[] brandAPrices = {11200, 5500, 4200, 9000, 2000, 1700, 1800, 2300};
            long[] brandBPrices = {10500, 5900, 3800, 9100, 2100, 2000, 2000, 2200};
            long[] brandCPrices = {10000, 6200, 3300, 9200, 2200, 1900, 2200, 2100};
            long[] brandDPrices = {10100, 5100, 3000, 9500, 2500, 1500, 2400, 2000};
            long[] brandEPrices = {10700, 5000, 3800, 9900, 2300, 1800, 2100, 2100};
            long[] brandFPrices = {11200, 7200, 4000, 9300, 2100, 1600, 2300, 1900};
            long[] brandGPrices = {10500, 5800, 3900, 9000, 2200, 1700, 2100, 2000};
            long[] brandHPrices = {10800, 6300, 3100, 9700, 2100, 1600, 2000, 2000};
            long[] brandIPrices = {11400, 6700, 3200, 9500, 2400, 1700, 1700, 2400};

            // 상품 저장
            Brand brandA = brandRepository.findBrandByName("A").get();
            Brand brandB = brandRepository.findBrandByName("B").get();
            Brand brandC = brandRepository.findBrandByName("C").get();
            Brand brandD = brandRepository.findBrandByName("D").get();
            Brand brandE = brandRepository.findBrandByName("E").get();
            Brand brandF = brandRepository.findBrandByName("F").get();
            Brand brandG = brandRepository.findBrandByName("G").get();
            Brand brandH = brandRepository.findBrandByName("H").get();
            Brand brandI = brandRepository.findBrandByName("I").get();

            ArrayList<Product> products = new ArrayList<>();
            addProduct(brandAPrices, brandA, products);
            addProduct(brandBPrices, brandB, products);
            addProduct(brandCPrices, brandC, products);
            addProduct(brandDPrices, brandD, products);
            addProduct(brandEPrices, brandE, products);
            addProduct(brandFPrices, brandF, products);
            addProduct(brandGPrices, brandG, products);
            addProduct(brandHPrices, brandH, products);
            addProduct(brandIPrices, brandI, products);
            productRepository.saveAll(products);

        };
    }

    // 저장할 상품 엔터티를 더하기
    private void addProduct(long[] brandAPrices, Brand brandA, ArrayList<Product> products) {
        Category[] categories = {TOP, COAT,PANTS,SNEAKERS, BAG, HAT, SOCKS, ACCESSORY};
        for (int i = 0; i< brandAPrices.length; i++){
            products.add(Product.builder()
                            .name("옷 이름")
                    .brand(brandA)
                    .category(categories[i])
                    .price(brandAPrices[i])
                    .build());
        }
    }
}
