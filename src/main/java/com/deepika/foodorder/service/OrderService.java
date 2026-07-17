package com.deepika.foodorder.service;

import com.deepika.foodorder.model.*;
import com.deepika.foodorder.repository.MenuItemRepository;
import com.deepika.foodorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestaurantService restaurantService;

    public Order placeOrder(Long customerId, Long restaurantId, List<OrderItem> requestedItems) {
        Customer customer = customerService.getCustomerById(customerId);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        double total = 0.0;
        for (OrderItem item : requestedItems) {
            MenuItem menuItem = menuItemRepository.findById(item.getMenuItem().getId())
                    .orElseThrow(() -> new RuntimeException("Menu item not found"));
            item.setMenuItem(menuItem);
            item.setSubTotal(menuItem.getPrice() * item.getQuantity());
            total += item.getSubTotal();
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setItems(requestedItems);
        order.setTotalAmount(total);
        order.setStatus(OrderStatus.PLACED);

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = getOrderById(orderId);
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
