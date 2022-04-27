package com.inditex.catalogservice.commons.exeption;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InditexException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int errorCode;

    public InditexException(ExceptionEnum clientErrorCode) {
        super(clientErrorCode.name());
        this.errorCode = clientErrorCode.getValue();
    }
}
