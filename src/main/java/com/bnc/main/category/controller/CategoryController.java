package com.bnc.main.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @GetMapping("/createCategoryForm")
    public String createCategoryForm(Model model) {

        return "views/product/createCategoryForm";
    }
}
