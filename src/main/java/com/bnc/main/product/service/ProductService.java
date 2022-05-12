package com.bnc.main.product.service;

import com.bnc.main.product.domain.Product;

public interface ProductService {
    Product create(Product product);

    void delete(Product product);

    void update(Long id, Product product);
}
