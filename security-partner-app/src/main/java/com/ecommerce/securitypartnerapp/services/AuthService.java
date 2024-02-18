package com.ecommerce.securitypartnerapp.services;

import com.ecommerce.securitypartnerapp.entities.User;
import com.ecommerce.securitypartnerapp.exceptions.AuthException;
import com.ecommerce.securitypartnerapp.models.RequestLogin;
import com.ecommerce.securitypartnerapp.models.ResponseTokens;
import com.ecommerce.securitypartnerapp.security.JwtProvider;
import com.ecommerce.securitypartnerapp.security.JwtAuthentication;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    // TODO Заменить Map на какое нибудь постоянное хранилище, например Redis
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    public ResponseTokens login(@NonNull RequestLogin authRequest) throws AuthException {
        final User user = userService.getByUsername(authRequest.getUsername())
                .orElseThrow(() -> new AuthException("User with username: " + authRequest.getUsername() + " not found!"));
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getUsername(), refreshToken);
            return new ResponseTokens(accessToken, refreshToken);
        } else {
            throw new AuthException("Incorrect password");
        }
    }

    public ResponseTokens getAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByUsername(login)
                        .orElseThrow(() -> new AuthException("User with username: " + login + " not found!"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new ResponseTokens(accessToken, null);
            }
        }
        return new ResponseTokens(null, null);
    }

    public ResponseTokens refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByUsername(login)
                        .orElseThrow(() -> new AuthException("User with username: " + login + " not found!"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getUsername(), newRefreshToken);
                return new ResponseTokens(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid JWT token!");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
