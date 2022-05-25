package com.bnc.main.product.controller;

import com.bnc.main.category.domain.Category;
import com.bnc.main.category.domain.CategoryRepository;
import com.bnc.main.product.service.DefaultProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final DefaultProductService ps;
    private final CategoryRepository categoryRepository;

    @GetMapping("/createProductForm")
    public String createProductForm(Model model){

        Optional<List<Category>> parentCategory = categoryRepository.findParentCategory();
        Optional<List<Category>> firstChildCategory = categoryRepository.findFirstChildCategory();

        model.addAttribute("parentCategory" , parentCategory);
        model.addAttribute("firstChildCategory" , firstChildCategory);

        return "views/product/createProduct";
    }
}
