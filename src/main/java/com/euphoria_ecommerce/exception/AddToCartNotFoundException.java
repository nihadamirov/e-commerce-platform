package com.euphoria_ecommerce.exception;

public class AddToCartNotFoundException extends RuntimeException{
    public AddToCartNotFoundException(String message) {
    super(message);
    }
}
