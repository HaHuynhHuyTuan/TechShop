package com.entity;

public class CartItem {
    private Product product; // Sản phẩm trong giỏ hàng
    private int quantity; // Số lượng sản phẩm trong giỏ hàng

    // Constructor CartItem nhận vào Product và quantity
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getter cho product
    public Product getProduct() {
        return product;
    }

    // Setter cho product
    public void setProduct(Product product) {
        this.product = product;
    }

    // Getter cho quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter cho quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Tính tổng tiền cho một sản phẩm (price * quantity)
    public double getTotal() {
        return product.getPrice() * quantity; // Giá của sản phẩm * số lượng
    }
}
