package edu.icet.trendify.controller;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.CartDto;
import edu.icet.trendify.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<CartDto>> saveCart(@RequestBody @Valid CartDto cartDto) {
        ResponseDto<CartDto> cartDtoResponseDto = cartService.saveCart(cartDto);

        if (Boolean.TRUE.equals(cartDtoResponseDto.getIsSuccess())) {
            return ResponseEntity.ok(cartDtoResponseDto);
        }

        return ResponseEntity.internalServerError().body(cartDtoResponseDto);
    }

    @PutMapping("")
    public ResponseEntity<ResponseDto<CartDto>> updateCart(@RequestBody @Valid CartDto cartDto) {
        ResponseDto<CartDto> cartDtoResponseDto = cartService.updateCart(cartDto);

        if (Boolean.TRUE.equals(cartDtoResponseDto.getIsSuccess())) {
            return ResponseEntity.ok(cartDtoResponseDto);
        }

        return ResponseEntity.internalServerError().body(cartDtoResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Boolean>> deleteCart(@PathVariable Long id) {
        Boolean responseDto = cartService.deleteCart(id);
        if (Boolean.TRUE.equals(responseDto)) {
            return ResponseEntity.ok(ResponseDto.success(null, "Cart deleted successfully!"));
        }
        return ResponseEntity.badRequest().body(ResponseDto.error("Cart not found by id: " + id));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<ResponseDto<List<CartDto>>> getAllSavedCartByCustomerId(@PathVariable("customer-id") Long customerId) {
        List<CartDto> responseDto = cartService.getAllSavedCartByCustomerId(customerId);
        if (!responseDto.isEmpty()) {
            return ResponseEntity.ok(ResponseDto.success(responseDto, "Carts found successfully!"));
        }
        return ResponseEntity.badRequest().body(ResponseDto.error("Carts not found by customer id: " + customerId));
    }
}
