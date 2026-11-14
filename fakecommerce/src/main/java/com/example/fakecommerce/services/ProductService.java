package com.example.fakecommerce.services;

import com.example.fakecommerce.dtos.CreateProductRequestDto;
import com.example.fakecommerce.dtos.GetProductResponseDto;
import com.example.fakecommerce.dtos.GetProductWithDetailsResponseDto;
import com.example.fakecommerce.repositories.ProductRepository;
import com.example.fakecommerce.schema.Category;
import com.example.fakecommerce.schema.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<GetProductResponseDto> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> GetProductResponseDto.builder()
                        .id(product.getId())
                        .title(product.getTitle())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .image(product.getImage())
                        .rating(product.getRating())
                        .build())
                .collect(Collectors.toList());
    }

    public GetProductResponseDto getProductById(Long id){
        return productRepository.findById(id)
                .map(product -> GetProductResponseDto.builder()
                        .id(product.getId())
                        .title(product.getTitle())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .image(product.getImage())
                        .rating(product.getRating())
                        .build())
                .orElseThrow(()->new RuntimeException("Product Not Found"));
    }

    public Product createProduct(CreateProductRequestDto requestDto){

        Category category = categoryService.getCategoryById(
                requestDto.getCategoryId()
        );

        Product newProduct = Product.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .image(requestDto.getImage())
                .price(requestDto.getPrice())
                .category(category)
                .rating(requestDto.getRating())
                .build();

        return productRepository.save(newProduct);

    }

    public GetProductWithDetailsResponseDto getProductWithDetailsById(Long id) {
        Product product = productRepository.findProductWithDetailsById(id).get(0);

        return GetProductWithDetailsResponseDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory().getName())
                .image(product.getImage())
                .rating(product.getRating())
                .build();

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
