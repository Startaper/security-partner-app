package com.ecommerce.securitypartnerapp.controllers;

import com.ecommerce.securitypartnerapp.exceptions.AuthException;
import com.ecommerce.securitypartnerapp.models.RequestLogin;
import com.ecommerce.securitypartnerapp.models.RequestRefreshJwt;
import com.ecommerce.securitypartnerapp.models.ResponseTokens;
import com.ecommerce.securitypartnerapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseTokens> login(@RequestBody RequestLogin authRequest) throws AuthException {
        final ResponseTokens token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/token")
    public ResponseEntity<ResponseTokens> getNewAccessToken(@RequestBody RequestRefreshJwt request) throws AuthException {
        final ResponseTokens token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseTokens> getNewRefreshToken(@RequestBody RequestRefreshJwt request) throws AuthException {
        final ResponseTokens token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}
