package com.inditex.catalogservice.commons.exeption;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NotFoundException extends InditexException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(ExceptionEnum clientErrorCode) {
        super(clientErrorCode);
    }
}
