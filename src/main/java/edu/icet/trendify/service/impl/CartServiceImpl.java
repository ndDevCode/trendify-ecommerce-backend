package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.CartDto;
import edu.icet.trendify.entity.user.CartEntity;
import edu.icet.trendify.repository.user.CartProductRepository;
import edu.icet.trendify.repository.user.CartRepository;
import edu.icet.trendify.repository.user.CustomerRepository;
import edu.icet.trendify.service.CartService;
import edu.icet.trendify.util.mapper.CartMapper;
import edu.icet.trendify.util.mapper.CartProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final CustomerRepository customerRepository;

    private final CartMapper cartMapper;
    private final CartProductMapper cartProductMapper;

    @Override
    @Transactional
    public ResponseDto<CartDto> saveCart(CartDto cartDto) {
        try {
            saveOrUpdateCart(cartDto);

            return ResponseDto.success(cartDto, "Cart saved successfully!");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.error("Error while saving cart!");
        }
    }

    @Override
    @Transactional
    public ResponseDto<CartDto> updateCart(CartDto cartDto) {
        if(cartRepository.existsById(cartDto.id())) {
            return ResponseDto.error("Cart not found by id: " + cartDto.id());
        }
        try {
            saveOrUpdateCart(cartDto);
            return ResponseDto.success(cartDto, "Cart updated successfully!");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.error("Error while updating cart!");
        }
    }

    protected void saveOrUpdateCart(CartDto cartDto) {
        CartEntity cartEntity = CartEntity.builder()
                .id(cartDto.id())
                .customer(customerRepository.findById(cartDto.customerId()).orElse(null))
                .build();

        CartEntity savedCart = cartRepository.save(cartEntity);

        cartDto.cartProductList().stream().map(cartProductMapper::toEntity).forEach(cartProduct -> {
            cartProduct.setCartId(savedCart.getId());
            cartProduct.setProductId(cartProduct.getProductId());
            cartProductRepository.save(cartProduct);
        });
    }

    @Override
    @Transactional
    public Boolean deleteCart(Long id) {
        if (!cartRepository.existsById(id)) {
            return false;
        }

        cartRepository.deleteById(id);
        cartProductRepository.deleteAllByCartId(id);
        return true;
    }

    @Override
    public List<CartDto> getAllSavedCartByCustomerId(Long customerId) {
        List<CartEntity> allCarts = cartRepository.findAllById(Collections.singleton(customerId));
        allCarts.forEach(cart -> cart.setCartProductList(cartProductRepository.findAllByCartId(cart.getId())));

        return allCarts.stream().map(cartMapper::toDto).toList();
    }
}
