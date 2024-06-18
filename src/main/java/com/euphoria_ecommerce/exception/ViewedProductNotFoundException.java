package com.euphoria_ecommerce.exception;

public class ViewedProductNotFoundException extends RuntimeException {
    public ViewedProductNotFoundException(String message) {
        super(message);
    }
}
