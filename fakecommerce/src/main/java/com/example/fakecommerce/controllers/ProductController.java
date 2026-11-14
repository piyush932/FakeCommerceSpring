package com.example.fakecommerce.controllers;

import com.example.fakecommerce.dtos.CreateProductRequestDto;
import com.example.fakecommerce.dtos.GetProductResponseDto;
import com.example.fakecommerce.dtos.GetProductWithDetailsResponseDto;
import com.example.fakecommerce.schema.Product;
import com.example.fakecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<GetProductResponseDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GetProductResponseDto getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/{id}/details")
    public GetProductWithDetailsResponseDto getProductWithDetailsById(@PathVariable Long id) {
        return productService.getProductWithDetailsById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody CreateProductRequestDto requestDto){
        return productService.createProduct(requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<Product> getProductsByCategory(@RequestParam("categoryName") String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }
}
