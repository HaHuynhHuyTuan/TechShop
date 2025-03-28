package com.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.CartItem;
import com.entity.Product;


@Service
public class CartService {
    private static List<CartItem> cartItems = new ArrayList<>();
    private final ProductService productService;

    // Constructor để inject ProductService
    public CartService(ProductService productService) {
        this.productService = productService;
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addProduct(int productId) {
        // Kiểm tra nếu sản phẩm đã tồn tại trong giỏ hàng thì chỉ tăng số lượng
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId() == productId) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return;
            }
        }
        // Nếu chưa có thì thêm mới vào danh sách giỏ hàng
        Product product = productService.getProductById(Long.valueOf(productId)); // Sử dụng instance của ProductService để lấy sản phẩm
        if (product != null) {
            cartItems.add(new CartItem(product, 1));
        }
    }

    // Lấy danh sách giỏ hàng
    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void removeProduct(int productId) {
        cartItems.removeIf(item -> item.getProduct().getId() == productId);
    }

    // Cập nhật số lượng sản phẩm
    public void updateProduct(int productId, int quantity) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId() == productId) {
                cartItem.setQuantity(quantity);
                return;
            }
        }
    }

    // Tính tổng tiền
    public static double getTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotal(); // Tổng = đơn giá x số lượng
        }
        return total;
    }

    // Xóa toàn bộ giỏ hàng
    public void clearCart() {
        cartItems.clear();
    }
}
