package com.example.fakecommerce.services;

import com.example.fakecommerce.dtos.CreateProductRequestDto;
import com.example.fakecommerce.repositories.ProductRepository;
import com.example.fakecommerce.schema.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product Not Found"));
    }

    public Product createProduct(CreateProductRequestDto requestDto){
        Product newProduct = Product.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .image(requestDto.getImage())
                .price(requestDto.getPrice())
                .category(requestDto.getCategory())
                .rating(requestDto.getRating())
                .build();

        return productRepository.save(newProduct);

    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategory(String category){
        return productRepository.findByCategory(category);
    }

    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }
}
