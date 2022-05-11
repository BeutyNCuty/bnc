package com.bnc.main.product.service;

import com.bnc.main.product.domain.Product;
import com.bnc.main.product.domain.ProductRepository;
import com.bnc.main.testSupport.BaseServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultProductServiceTest extends BaseServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void 상품_생성_성공() {
        Product product = new Product("옷", 123, "구찌");

        product = productService.create(product);

        Product foundProduct = productRepository.findById(product.getId()).orElseThrow();

        assertThat(foundProduct).isEqualTo(product);
    }
}