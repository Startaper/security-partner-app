package com.ecommerce.securitypartnerapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ResponseError {

    private int errorCode;
    private String error;
    private String message;
    private Date timestamp;

}
