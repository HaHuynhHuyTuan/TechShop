package com.Controller;

import com.Service.ProductService;
import com.entity.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ Lọc sản phẩm theo danh mục (Phân trang)
    @GetMapping("/list-by-category/{categoryId}")
    public String listByCategory(@PathVariable Long categoryId, 
                                 @RequestParam(defaultValue = "0") int page, 
                                 @RequestParam(defaultValue = "5") int size, 
                                 Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getProductsByCategory(categoryId, pageable);

        model.addAttribute("productPage", productPage);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "product-list";
    }

    // ✅ Tìm kiếm sản phẩm và hiển thị trang product-list
    @GetMapping("/search")
    public String searchProducts(@RequestParam String name, 
                                 @PageableDefault(size = 5) Pageable pageable, 
                                 Model model) {
        Page<Product> products = productService.searchProducts(name, pageable); // ✅ Đúng
        model.addAttribute("productPage", products);
        model.addAttribute("searchTerm", name);
        return "product-list";
    }
    
    @GetMapping("/list")
    public String listProducts(@RequestParam(name = "name", required = false) String name, Model model) {
        if (name == null || name.trim().isEmpty()) {
            return "redirect:/home/index"; // Nếu name trống, chuyển về trang Home
        }

        List<Product> products = productService.searchByName(name);
        model.addAttribute("productPage", new PageImpl<>(products));
        model.addAttribute("searchKeyword", name);
        return "product-list";
    }

}
