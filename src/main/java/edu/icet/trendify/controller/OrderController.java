package edu.icet.trendify.controller;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.order.OrderDto;
import edu.icet.trendify.service.OrderService;
import edu.icet.trendify.util.enums.OrderStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<OrderDto>> placeOrder(@RequestBody @Valid OrderDto orderDto) {
        ResponseDto<OrderDto> order = orderService.placeOrder(orderDto);
        if (Boolean.TRUE.equals(order.getIsSuccess())) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.badRequest().body(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ResponseDto<OrderDto>> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus orderStatus) {
        ResponseDto<OrderDto> order = orderService.updateOrderStatus(orderId, orderStatus);
        if (Boolean.TRUE.equals(order.getIsSuccess())) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.badRequest().body(order);
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<OrderDto>>> getAllOrder() {
        List<OrderDto> orders = orderService.getAllOrder();
        return ResponseEntity.ok(ResponseDto.success(orders, "Orders fetched successfully!"));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<ResponseDto<List<OrderDto>>> getOrderByCustomerId(@PathVariable Long customerId) {
        List<OrderDto> orders = orderService.getOrderByCustomerId(customerId);
        return ResponseEntity.ok(ResponseDto.success(orders, "Orders fetched successfully!"));
    }
}
