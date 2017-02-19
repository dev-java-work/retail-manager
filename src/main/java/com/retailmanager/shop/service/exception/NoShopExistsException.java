package com.retailmanager.shop.service.exception;

public class NoShopExistsException extends RuntimeException {

    public NoShopExistsException(final String message) {
        super(message);
    }
}
