package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.dto.WishlistDto;
import com.euphoria_ecommerce.exception.ProductNotFoundException;
import com.euphoria_ecommerce.exception.UserNotFoundException;
import com.euphoria_ecommerce.exception.WishlistNotFoundException;
import com.euphoria_ecommerce.model.Price;
import com.euphoria_ecommerce.model.Product;
import com.euphoria_ecommerce.model.User;
import com.euphoria_ecommerce.model.Wishlist;
import com.euphoria_ecommerce.repository.ProductRepository;
import com.euphoria_ecommerce.repository.UserRepository;
import com.euphoria_ecommerce.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<WishlistDto> getAllWishlist() {
        return wishlistRepository.findAll()
                .stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public WishlistDto getWishlistById(int id) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new WishlistNotFoundException("Wishlist not found with id: " + id));
        return modelMapper.map(wishlist, WishlistDto.class);
    }

    public WishlistDto createWishlist(WishlistDto wishlistDto) {
        Wishlist wishlist = new Wishlist();

        Product product = productRepository.findById(wishlistDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id:" + wishlistDto.getProductId()));
        User user = userRepository.findById(wishlistDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + wishlistDto.getUserId()));

        wishlist.setId(wishlist.getId());
        wishlist.setProduct(product);
        wishlist.setUser(user);
        wishlist.setQuantity(wishlistDto.getQuantity());
        double total = 0;
        for (Price price : product.getPrice()) {
            total = wishlistDto.getQuantity() * price.getAmount();
            log.info("Price is: " + price.getAmount());
        }
        log.info("total is: " + total);
        wishlist.setTotalPrice(total);

        Wishlist savedWishlist = wishlistRepository.save(wishlist);
        return modelMapper.map(savedWishlist, WishlistDto.class);
    }

    public WishlistDto updateWishlist(int id, WishlistDto wishlistDto) {
        Wishlist foundedWishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new WishlistNotFoundException("Wishlist not found with id: " + id));
        Product product = productRepository.findById(wishlistDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id:" + wishlistDto.getProductId()));
        User user = userRepository.findById(wishlistDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + wishlistDto.getUserId()));

        foundedWishlist.setQuantity(wishlistDto.getQuantity());
        foundedWishlist.setProduct(product);
        foundedWishlist.setUser(user);
        double total = 0;
        for (Price price : product.getPrice()) {
            total = wishlistDto.getQuantity() * price.getAmount();
            log.info("Price is: " + price.getAmount());
        }
        log.info("total is: " + total);
        foundedWishlist.setTotalPrice(total);

        return modelMapper.map(wishlistRepository.save(foundedWishlist), WishlistDto.class);
    }

    public void deleteWishlist(int id) {
        Wishlist foundedWishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new WishlistNotFoundException("Wishlist not found with id: " + id));
        wishlistRepository.deleteById(id);
    }

    private WishlistDto convertEntityToDto(Wishlist wishlist) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        WishlistDto wishlistDto = new WishlistDto();
        wishlistDto = modelMapper.map(wishlist, WishlistDto.class);
        return wishlistDto;
    }
}
