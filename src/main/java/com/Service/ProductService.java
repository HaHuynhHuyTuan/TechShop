package com.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.DAO.CategoryDAO;
import com.DAO.ProductDAO;
import com.entity.Category;
import com.entity.Product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDAO productRepository;
    private final CategoryDAO categoryRepository;

    public ProductService(ProductDAO productRepository, CategoryDAO categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Lấy danh sách sản phẩm theo categoryId và phân trang
    public Page<Product> getProductsByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable);
    }

    // Lấy tất cả sản phẩm với phân trang
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    // Tìm sản phẩm theo ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // Lấy danh sách sản phẩm theo category
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    // Thêm một sản phẩm mới vào cơ sở dữ liệu
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Cập nhật thông tin sản phẩm
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // Xóa sản phẩm theo ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
 // Tìm kiếm sản phẩm có phân trang
    public Page<Product> searchProducts(String name, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    // Tìm kiếm sản phẩm không phân trang
    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        return optional.orElse(null);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
    
    
}
