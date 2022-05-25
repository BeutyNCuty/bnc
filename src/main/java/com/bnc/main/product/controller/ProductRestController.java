package com.bnc.main.product.controller;


import com.bnc.main.product.controller.dto.ProductCreateDTO;
import com.bnc.main.product.domain.Product;
import com.bnc.main.product.service.DefaultProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final DefaultProductService ps;


    @PostMapping("/createProduct")
    public String createProduct(ProductCreateDTO productCreateDTO){

        Product product = new Product();

        Product entityProduct = product.dtoToEntity(productCreateDTO);

        ps.create(entityProduct);

        return "index";
    }
}
