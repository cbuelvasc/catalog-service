package com.inditex.catalogservice.commons.exeption;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Relation(collectionRelation = "api", itemRelation = "api")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = Visibility.NONE,
        setterVisibility = Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse extends RepresentationModel<ExceptionResponse> {

    private int errorCode;
    private String externalErrorCode;
    private List<String> errorMessages = new ArrayList<>();

    public ExceptionResponse(int errorCode, String externalErrorCode, String... errorMessages) {
        this.errorCode = errorCode;
        this.externalErrorCode = externalErrorCode;
        this.errorMessages = Arrays.asList(errorMessages);
    }
}