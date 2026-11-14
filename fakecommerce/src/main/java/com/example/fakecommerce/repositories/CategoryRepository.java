package com.example.fakecommerce.repositories;

import com.example.fakecommerce.schema.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
