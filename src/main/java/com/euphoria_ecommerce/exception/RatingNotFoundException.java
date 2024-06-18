package com.euphoria_ecommerce.exception;

public class RatingNotFoundException extends RuntimeException{
    public RatingNotFoundException(String message) {
        super(message);
    }
}
