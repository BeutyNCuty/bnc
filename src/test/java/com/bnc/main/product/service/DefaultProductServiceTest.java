package com.bnc.main.product.service;

import com.bnc.main.product.domain.Product;
import com.bnc.main.product.domain.ProductRepository;
import com.bnc.main.product.domain.ProductStatus;
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

    @Test
    void 상품_삭제_성공() {
        Product product = new Product("옷", 123, "구찌");

        productService.delete(product);

        Product foundProduct = productRepository.findById(product.getId()).orElseThrow();

        assertThat(foundProduct.getProductStatus()).isEqualTo(ProductStatus.DELETED);
    }

    @Test
    void 상품_수정_성공() {
        Product product = new Product("옷", 123, "구찌");

        product = productService.create(product);

        Product params = new Product("바지", 123, "프라다");

        productService.update(product.getId(), params);

        Product foundProduct = productRepository.findById(product.getId()).orElseThrow();

        assertThat(foundProduct).isEqualTo(product);
    }
}
