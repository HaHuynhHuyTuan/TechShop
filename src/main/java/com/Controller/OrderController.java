package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Service.OrderService;
import com.Service.CartService;
import com.entity.CartItem;
import com.entity.Order;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    // Trang Checkout
    @GetMapping("/checkout")
    public String checkoutPage(Model model) {
        // Lấy giỏ hàng từ CartService
        List<CartItem> cartItems = cartService.getCartItems();
        if (cartItems == null || cartItems.isEmpty()) {
            return "redirect:/cart"; // Nếu giỏ hàng trống, quay lại trang cart
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", cartService.getTotal());
        return "checkout";
    }

    // Xử lý đặt hàng (Hợp nhất hai phương thức)
    @PostMapping("/checkout")
    public String placeOrder(@RequestParam String phone, 
                             @RequestParam String address,
                             @RequestParam(required = false) String note) {
        // Lấy giỏ hàng từ CartService
        List<CartItem> cartItems = cartService.getCartItems();
        if (cartItems == null || cartItems.isEmpty()) {
            return "redirect:/cart"; // Nếu giỏ hàng trống, không thể đặt hàng
        }

        try {
            // Gọi service để tạo đơn hàng
            Order order = orderService.createOrder(phone, address, note, cartItems);

            // Xóa giỏ hàng sau khi đặt hàng thành công
            cartService.clearCart();

            // Chuyển hướng đến trang thành công
            return "redirect:/order-success?orderId=" + order.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/checkout?error=true"; // Trả về trang checkout nếu có lỗi
        }
    }

    // Trang xác nhận đặt hàng thành công
    @GetMapping("/order-success")
    public String orderSuccessPage() {
        return "order-success"; 
    }

}
