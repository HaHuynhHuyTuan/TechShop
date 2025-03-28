package com.Controller;

import com.Service.CartService;
import com.entity.CartItem;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String viewCart(Model model) {
        List<CartItem> cartItems = cartService.getCartItems(); // Get cart items from the service
        double total = cartService.getTotal(); // Get total price

        model.addAttribute("cartItems", cartItems); // Add cart items to the model
        model.addAttribute("total", total);         // Add total to the model

        return "cart"; // Return the "cart" template
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable int id, Model model) {
        cartService.addProduct(id); // Add product to the cart
        model.addAttribute("cartItems", cartService.getCartItems()); // Update cart items
        model.addAttribute("total", cartService.getTotal()); // Update total
        return "cart"; // Return to the cart view
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable int id, Model model) {
        cartService.removeProduct(id); // Remove product from the cart
        model.addAttribute("cartItems", cartService.getCartItems()); // Update cart items
        model.addAttribute("total", cartService.getTotal()); // Update total
        return "cart"; // Return to the cart view
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam int id, @RequestParam int quantity, Model model) {
        cartService.updateProduct(id, quantity); // Update product quantity in the cart
        model.addAttribute("cartItems", cartService.getCartItems()); // Update cart items
        model.addAttribute("total", cartService.getTotal()); // Update total
        return "cart"; // Return to the cart view
    }

    @GetMapping("/clear")
    public String clearCart(Model model) {
        cartService.clearCart(); // Clear the cart
        model.addAttribute("cartItems", cartService.getCartItems()); // Update cart items
        model.addAttribute("total", cartService.getTotal()); // Update total
        return "cart"; // Return to the cart view
    }
}
