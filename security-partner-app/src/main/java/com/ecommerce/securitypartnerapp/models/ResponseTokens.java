package com.ecommerce.securitypartnerapp.models;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ResponseTokens {

    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;

}
