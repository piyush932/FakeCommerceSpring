package com.example.fakecommerce.services;

import com.example.fakecommerce.dtos.CreateCategoryRequestDto;
import com.example.fakecommerce.exceptions.ResourceNotFoundException;
import com.example.fakecommerce.repositories.CategoryRepository;
import com.example.fakecommerce.schema.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(CreateCategoryRequestDto requestDto){
        Category newCategory = Category.builder()
                .name(requestDto.getName())
                .build();

        return categoryRepository.save(newCategory);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category with id " + id + " not found"));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
