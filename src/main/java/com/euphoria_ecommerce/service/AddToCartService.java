package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.dto.AddToCartDto;
import com.euphoria_ecommerce.exception.AddToCartNotFoundException;
import com.euphoria_ecommerce.exception.ProductNotFoundException;
import com.euphoria_ecommerce.exception.UserNotFoundException;
import com.euphoria_ecommerce.model.*;
import com.euphoria_ecommerce.repository.AddToCartRepository;
import com.euphoria_ecommerce.repository.ProductRepository;
import com.euphoria_ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddToCartService {
    private final AddToCartRepository addToCartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public List<AddToCartDto> getAllAddToCarts() {

        return addToCartRepository.findAll()
                .stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public AddToCartDto getAddToCartById(int id) {
        AddToCart addToCart = addToCartRepository.findById(id)
                .orElseThrow(() -> new AddToCartNotFoundException("Add to cart not found with id: " + id));
        return modelMapper.map(addToCart, AddToCartDto.class);
    }

    public AddToCartDto createAddToCart(AddToCartDto addToCartDto) {
        AddToCart addToCart = new AddToCart();
        Product product = productRepository.findById(addToCartDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id:" + addToCartDto.getProductId()));
        User user = userRepository.findById(addToCartDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + addToCartDto.getUserId()));

        addToCart.setId(addToCart.getId());
        addToCart.setQuantity(addToCartDto.getQuantity());
        addToCart.setShipping(addToCartDto.getShipping());
        addToCart.setProduct(product);
        addToCart.setUser(user);
        double addedTimePrice = 0;
        double totalPrice = 0;
        for (Price price : product.getPrice()) {
            addedTimePrice = price.getAmount();
            totalPrice = addToCartDto.getQuantity() * addedTimePrice;
        }
        addToCart.setAddedTimePrice(addedTimePrice);
        addToCart.setTotalPrice(totalPrice);

        return modelMapper.map(addToCartRepository.save(addToCart), AddToCartDto.class);
    }

    public AddToCartDto updateAddToCart(int id, AddToCartDto addToCartDto) {
        AddToCart foundedCart = addToCartRepository.findById(id)
                .orElseThrow(() -> new AddToCartNotFoundException("Add to cart not found with id: " + id));
        Product product = productRepository.findById(addToCartDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id:" + addToCartDto.getProductId()));
        User user = userRepository.findById(addToCartDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + addToCartDto.getUserId()));

        foundedCart.setQuantity(addToCartDto.getQuantity());
        foundedCart.setShipping(addToCartDto.getShipping());
        foundedCart.setProduct(product);
        foundedCart.setUser(user);
        double addedTimePrice = 0;
        double totalPrice = 0;
        for (Price price : product.getPrice()) {
            addedTimePrice = price.getAmount();
            totalPrice = addToCartDto.getQuantity() * addedTimePrice;
        }
        foundedCart.setAddedTimePrice(addedTimePrice);
        foundedCart.setTotalPrice(totalPrice);
        return modelMapper.map(addToCartRepository.save(foundedCart), AddToCartDto.class);
    }

    public void deleteAddToCart(int id) {
        AddToCart foundedCart = addToCartRepository.findById(id)
                .orElseThrow(() -> new AddToCartNotFoundException("Add to cart not found with id: " + id));
        addToCartRepository.deleteById(id);
    }


    public double subTotal(long userId) {
        List<AddToCart> addToCarts = addToCartRepository.findByUserId(userId);

        double price = 0;
        double subtotal = 0;
        for (AddToCart prices : addToCarts) {
            price += prices.getTotalPrice();

        }
        log.info("Added time price is: " + price);
        subtotal += price;
        log.info("Subtotal in subTotal method is: " + subtotal);
        return subtotal;
    }

    public double grantTotal(long userId) {
        double subtotal = subTotal(userId);
        List<AddToCart> addToCarts = addToCartRepository.findByUserId(userId);
        double grandTotal = 0;
        double totalShipping = 0;
        for (AddToCart shipping : addToCarts) {
            totalShipping += shipping.getShipping();
        }
        log.info("totalShipping is: " + totalShipping);
        grandTotal = totalShipping + subtotal;
        return grandTotal;
    }

    private AddToCartDto convertEntityToDto(AddToCart addToCart) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        AddToCartDto addToCartDto = new AddToCartDto();
        addToCartDto = modelMapper.map(addToCart, AddToCartDto.class);
        return addToCartDto;
    }
}
