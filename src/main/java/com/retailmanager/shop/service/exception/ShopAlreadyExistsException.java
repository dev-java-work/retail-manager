package com.retailmanager.shop.service.exception;

public class ShopAlreadyExistsException extends RuntimeException {

    public ShopAlreadyExistsException(final String message) {
        super(message);
    }
}
