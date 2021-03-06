package com.bnc.main.product.service;

import com.bnc.main.product.domain.Product;
import com.bnc.main.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        product.delete();
    }

    @Override
    public void update(Long id, Product prams) {
        Product product = productRepository.findById(id).orElseThrow();

        product.change(prams.getName(), prams.getPrice(), prams.getBrand());
    }
}
