
package com.salesmanager.core.business.utils;

public class InvalidCardNumberException extends RuntimeException {
    public InvalidCardNumberException(String message) {
        super(message);
    }
}