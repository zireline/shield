package com.splitscale.shield.refreshtoken;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RefreshTokenManager {
  private TokenStorage tokenStorage;
  private long tokenExpiration = 30 * 24 * 60 * 60 * 1000; // 30 days in milliseconds

  public RefreshTokenManager(TokenStorage tokenStorage) {
    this.tokenStorage = tokenStorage;
  }

  public String generateRefreshToken(String userId) {
    String refreshToken = UUID.randomUUID().toString();
    long expirationTime = System.currentTimeMillis() + tokenExpiration;

    // Store the refresh token using the provided token storage
    tokenStorage.storeToken(refreshToken, new TokenInfo(userId, expirationTime));

    return refreshToken;
  }

  public boolean validateRefreshToken(String refreshToken, String userId) {
    TokenInfo storedToken = tokenStorage.retrieveToken(refreshToken);

    if (storedToken != null) {
      long currentTimeMillis = System.currentTimeMillis();

      if (storedToken.getUserId().equals(userId) && storedToken.getExpiresAt() > currentTimeMillis) {
        // Valid refresh token
        return true;
      }
    }

    // Invalid or expired refresh token
    return false;
  }

}