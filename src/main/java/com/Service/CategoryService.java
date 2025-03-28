package com.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.DAO.CategoryDAO;
import com.entity.Category;

@Service
public class CategoryService {
	private final CategoryDAO categoryRepository;

    public CategoryService(CategoryDAO categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
