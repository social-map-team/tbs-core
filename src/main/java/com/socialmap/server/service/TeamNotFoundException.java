package com.socialmap.server.service;

/**
 * Created by yy on 3/25/15.
 */
public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException() {

    }

    public TeamNotFoundException(String message) {
        super(message);
    }

    public TeamNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeamNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TeamNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
