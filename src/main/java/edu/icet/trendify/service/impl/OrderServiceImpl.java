package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.order.OrderDto;
import edu.icet.trendify.entity.order.OrderEntity;
import edu.icet.trendify.entity.order.OrderProductEntity;
import edu.icet.trendify.repository.order.OrderProductRepository;
import edu.icet.trendify.repository.order.OrderRepository;
import edu.icet.trendify.repository.product.ProductRepository;
import edu.icet.trendify.repository.user.CustomerRepository;
import edu.icet.trendify.service.OrderService;
import edu.icet.trendify.util.enums.OrderStatus;
import edu.icet.trendify.util.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;


    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public ResponseDto<OrderDto> placeOrder(OrderDto orderDto) {
        try {
            OrderEntity orderEntity = orderMapper.toEntity(orderDto);
            orderEntity.setCustomer(customerRepository.findById(orderDto.customerId()).orElse(null));
            orderEntity.setOrderPlacedAt(LocalDateTime.now());
            orderRepository.save(orderEntity);

            orderDto.orderProducts().forEach(orderProduct ->
                    orderProductRepository.save(
                            new OrderProductEntity(
                                    orderEntity.getId(),
                                    orderProduct.productId(),
                                    orderProduct.quantity(),
                                    orderProduct.colorId(),
                                    orderProduct.price(),
                                    orderEntity,
                                    productRepository.findById(orderProduct.productId()).orElse(null)
                            )
                    )
            );

            return ResponseDto.success(orderMapper.toDto(orderEntity), "Order placed successfully!");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.error(e.getMessage());
        }
    }

    @Override
    public ResponseDto<OrderDto> updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);
        if (orderEntity.isPresent()) {
            orderEntity.get().setOrderStatus(orderStatus);
            orderRepository.save(orderEntity.get());
            return ResponseDto.success(orderMapper.toDto(orderEntity.get()), "Order status updated successfully!");
        }

        return ResponseDto.error("Order not found!");
    }

    @Override
    public List<OrderDto> getAllOrder() {
        return orderRepository.findAll().stream().map(orderMapper::toDto).toList();
    }

    @Override
    public List<OrderDto> getOrderByCustomerId(Long id) {
        return orderRepository.findByCustomer_Id(id).stream().map(orderMapper::toDto).toList();
    }
}
