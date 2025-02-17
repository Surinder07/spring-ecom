package com.codeqube.spring3ecom.repository;

import com.codeqube.spring3ecom.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
