package com.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DAO.OrderDAO;
import com.DAO.OrderDetailDAO;
import com.entity.CartItem;
import com.entity.Order;
import com.entity.OrderDetail;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderRepository;

    @Autowired
    private OrderDetailDAO orderDetailRepository;

    @Transactional
    public Order createOrder(String phone, String address, String note, List<CartItem> cartItems) {
        // Tạo đơn hàng mới
        Order newOrder = new Order();
        newOrder.setPhone(phone);
        newOrder.setAddress(address);
        newOrder.setNote(note);
        newOrder.setStatus(0); // Đơn hàng mới tạo có trạng thái "chờ xác nhận"

        // Lưu đơn hàng vào database trước để có ID
        Order savedOrder = orderRepository.save(newOrder);

        // Chuyển đổi CartItem thành OrderDetail
        List<OrderDetail> orderDetails = cartItems.stream().map(cartItem -> {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder); // Gán đơn hàng đã lưu
            detail.setProduct(cartItem.getProduct());
            detail.setQuantity(cartItem.getQuantity());
            detail.setPrice(cartItem.getProduct().getPrice().doubleValue()); // Lấy giá từ sản phẩm
            return detail;
        }).collect(Collectors.toList());

        // Lưu danh sách OrderDetail vào database
        orderDetailRepository.saveAll(orderDetails);

        return savedOrder;
    }
}
