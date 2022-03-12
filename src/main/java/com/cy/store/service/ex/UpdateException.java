package com.cy.store.service.ex;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description: TODO
 * @date 2022/3/12 20:01
 */
public class UpdateException extends RuntimeException{
    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
