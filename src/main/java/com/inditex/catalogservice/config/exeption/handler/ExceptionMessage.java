package com.inditex.catalogservice.config.exeption.handler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ExceptionMessage {

    private final MessageSource messageSource;

    public ExceptionMessage(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(int code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(String.valueOf(code), null, locale);
    }

    public String getMessage(String i18n) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(i18n, null, locale);
    }
}
