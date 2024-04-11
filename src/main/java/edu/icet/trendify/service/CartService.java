package edu.icet.trendify.service;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.CartDto;

import java.util.List;

public interface CartService {
    ResponseDto<CartDto> saveCart(CartDto cartDto);
    ResponseDto<CartDto> updateCart(CartDto cartDto);
    Boolean deleteCart(Long id);
    List<CartDto> getAllSavedCartByCustomerId(Long customerId);
}
