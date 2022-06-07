package com.bnc.main.product.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void 상품_생성_성공() {
        Product product = new Product("티셔츠", 123, "구찌");

        product = productRepository.save(product);

        Product foundProduct = productRepository.findById(product.getId()).orElseThrow();
        assertThat(foundProduct).isEqualTo(product);
    }
}
