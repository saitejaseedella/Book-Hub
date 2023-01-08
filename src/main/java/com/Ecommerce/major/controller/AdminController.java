package com.Ecommerce.major.controller;

import com.Ecommerce.major.dto.ProductDTO;
import com.Ecommerce.major.model.Category;
import com.Ecommerce.major.service.CategoryService;
import com.Ecommerce.major.service.ProcustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
  CategoryService categoryService;

    @Autowired
    ProcustService procustService;

    @GetMapping("/admin")
    public String adminHome() {

        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCat(Model model) {
        model.addAttribute("categories",categoryService.getAllCategory());
        return "categories";
    }

   @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model) {
      model.addAttribute("category",new Category());
       return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category",category.get());
            return "categoriesAdd";
        }
        else
        return "404";
    }

    //product section

    @GetMapping("/admin/products")
    public String products(Model model) {
        model.addAttribute("products",procustService.getAllProduct());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String productAddGet(Model model) {
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }

}
