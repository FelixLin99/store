package com.cy.store.service.ex;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/18 13:48
 */
public class AddressNotFoundException extends ServiceException{
    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(String message) {
        super(message);
    }

    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AddressNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
