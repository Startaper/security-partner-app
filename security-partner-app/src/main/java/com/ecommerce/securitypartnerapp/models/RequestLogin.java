package com.ecommerce.securitypartnerapp.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RequestLogin {

    private String username;
    private String password;

}
