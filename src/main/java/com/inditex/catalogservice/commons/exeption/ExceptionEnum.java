package com.inditex.catalogservice.commons.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    REQUEST_INPUT_ERROR(400),
    NOT_FOUND_ERROR(404),
    CONFLICT(409),
    SERVER_ERROR(500),

    HTTP_MESSAGE_NOT_READABLE(4001),
    FIELD_FORMAT_INVALID(4002),
    JSON_FORMAT_INVALID(4003);

    private final int value;

    private static final Map<Integer, ExceptionEnum> map = new HashMap<>();

    static {
        for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
            map.put(ExceptionEnum.valueOf(exceptionEnum.name()).value, exceptionEnum);
        }
    }

    public static ExceptionEnum valueOf(int value) {
        return map.get(value);
    }
}
