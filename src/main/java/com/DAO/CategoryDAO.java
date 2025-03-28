package com.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Category;


public interface CategoryDAO extends JpaRepository<Category, Long> {
}
