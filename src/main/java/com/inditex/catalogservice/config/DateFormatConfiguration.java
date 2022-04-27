package com.inditex.catalogservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.FormattingConversionService;

import java.time.format.DateTimeFormatter;

//@Configuration(proxyBeanMethods = false)
class DateFormatConfiguration {

    private final DateTimeFormatterRegistrar dateTimeFormatterRegistrar = new DateTimeFormatterRegistrar();

    public DateFormatConfiguration() {
        /*dateTimeFormatterRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateTimeFormatterRegistrar.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm:ss"));
        dateTimeFormatterRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));*/
    }

    @Autowired
    public void configure(final FormattingConversionService conversionService) {
        dateTimeFormatterRegistrar.registerFormatters(conversionService);
    }
}
