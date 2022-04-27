package com.inditex.catalogservice.commons.exeption;

public class UnProcessableEntityException extends InditexException {

    private static final long serialVersionUID = 1L;

    public UnProcessableEntityException(ExceptionEnum clientErrorCode) {
        super(clientErrorCode);
    }
}
