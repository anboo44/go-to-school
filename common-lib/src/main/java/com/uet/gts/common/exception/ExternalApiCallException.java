package com.uet.gts.common.exception;

import static com.uet.gts.common.constant.ErrorList.UNAVAILABLE_SERVICE;
import static com.uet.gts.common.constant.ErrorList.UNKNOWN_ERROR;

public class ExternalApiCallException extends RuntimeException {
    private final int httpCode;
    private final String message;

    public ExternalApiCallException(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    @Override
    public String getMessage() {
        switch(httpCode) {
            case 503:
                return String.format("%s: %s", message, UNAVAILABLE_SERVICE);
            default:
                return String.format("%s: %s", message, UNKNOWN_ERROR);
        }
    }
}
