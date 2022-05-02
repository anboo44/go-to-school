package com.uet.gts.common.exception;

public class ExistedEntityException extends RuntimeException {
    public ExistedEntityException(String message) {
        super(message);
    }
}
