package com.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Service.ProductService;
import com.entity.Product;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/admin/product")
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        List<String> categories = List.of("Game", "Electronic Devices", "Furniture", "Clothing", "Books");

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "admin/product-list";
    }

    
 // Danh sách Category cố định
    private final List<String> categories = Arrays.asList(
            "Game", "Electronic Devices", "Furniture", "Clothing", "Books"
    );

    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/product";
    }
    
    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", productService.findAll());
            return "admin/product";
        }
        productService.save(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        
        if (product == null) { // Kiểm tra nếu không tìm thấy sản phẩm
            return "redirect:/admin/product";
        }

        List<String> categories = List.of("Game", "Electronic Devices", "Furniture", "Clothing", "Books");
        
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "admin/product-form";
    }



    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/admin/product";
    }
    
    @PostMapping("/admin/product/update")
    public String updateProduct(@ModelAttribute Product product,
                                @RequestParam("imageFile") MultipartFile file,
                                @RequestParam("existingImage") String existingImage) {
        try {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Path filePath = Paths.get("uploads", fileName);
                Files.write(filePath, file.getBytes());
                product.setImage(fileName);
            } else {
                product.setImage(existingImage); // Giữ ảnh cũ nếu không chọn ảnh mới
            }

            productService.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/product";
    }


    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/product";
    }
}
