package com.DAO;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Category;
import com.entity.Product;


public interface ProductDAO extends JpaRepository<Product, Long> {
    // Lấy tất cả sản phẩm theo Category mà không phân trang
    List<Product> findByCategory(Category category);

    // Lấy tất cả sản phẩm với phân trang
    Page<Product> findAll(Pageable pageable);

    // Lấy sản phẩm theo categoryId với phân trang
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    // Lấy sản phẩm theo Category với phân trang
    Page<Product> findByCategory(Category category, Pageable pageable);
    
    // tìm kiếm sản phẩm bằng nút search
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<Product> findByNameContainingIgnoreCase(String name); // Không phân trang

    
}

