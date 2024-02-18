package com.ecommerce.securitypartnerapp.security;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record RefreshToken(UUID id, String subject, List<String> authorities, Instant createdAt, Instant expiresAt) {
}
