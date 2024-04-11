package edu.icet.trendify.service;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.order.OrderDto;
import edu.icet.trendify.util.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    ResponseDto<OrderDto> placeOrder(OrderDto orderDto);
    ResponseDto<OrderDto> updateOrderStatus(Long orderId, OrderStatus orderStatus);
    List<OrderDto> getAllOrder();
    List<OrderDto> getOrderByCustomerId(Long id);
}
