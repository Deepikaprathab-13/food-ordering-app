package com.deepika.foodorder;

import com.deepika.foodorder.model.*;
import com.deepika.foodorder.repository.MenuItemRepository;
import com.deepika.foodorder.repository.OrderRepository;
import com.deepika.foodorder.service.CustomerService;
import com.deepika.foodorder.service.OrderService;
import com.deepika.foodorder.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder_calculatesTotalCorrectly() {
        Customer customer = new Customer(1L, "Deepika", "deepika@test.com", "9999999999", "Chennai");
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", "Chennai", "South Indian", new ArrayList<>());

        MenuItem menuItem = new MenuItem(1L, "Dosa", 50.0, true, restaurant);

        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItem(menuItem);
        orderItem.setQuantity(2);

        List<OrderItem> items = new ArrayList<>();
        items.add(orderItem);

        when(customerService.getCustomerById(1L)).thenReturn(customer);
        when(restaurantService.getRestaurantById(1L)).thenReturn(restaurant);
        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(menuItem));
        when(orderRepository.save(org.mockito.ArgumentMatchers.any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Order result = orderService.placeOrder(1L, 1L, items);

        assertEquals(100.0, result.getTotalAmount());
        assertEquals(OrderStatus.PLACED, result.getStatus());
    }
}
