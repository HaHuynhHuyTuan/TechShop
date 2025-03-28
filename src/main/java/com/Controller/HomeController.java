package com.Controller;

import org.springframework.data.domain.Pageable;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Service.ProductService;
import com.entity.Product;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "0") int page, 
                        @RequestParam(defaultValue = "5") int size, 
                        Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getAllProducts(pageable);

        model.addAttribute("productPage", productPage);  // Thêm đối tượng Page vào model
        model.addAttribute("currentPage", page);  // Trang hiện tại
        model.addAttribute("totalPages", productPage.getTotalPages());  // Tổng số trang
        model.addAttribute("totalItems", productPage.getTotalElements());  // Tổng số sản phẩm

        return "home";  // Trả về view 'home'
    }

}


