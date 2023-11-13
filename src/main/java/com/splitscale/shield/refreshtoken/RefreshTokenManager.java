package com.splitscale.shield.refreshtoken;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RefreshTokenManager {
  private TokenStorage tokenStorage;

  public RefreshTokenManager(TokenStorage tokenStorage) {
    this.tokenStorage = tokenStorage;
  }

  public String generateRefreshToken(String userId, long expirationTime, TimeUnit timeUnit) {
    long expirationMillis = System.currentTimeMillis() + timeUnit.toMillis(expirationTime);
    Date expirationDate = new Date(expirationMillis);
    String refreshToken = UUID.randomUUID().toString();

    // Store the refresh token using the provided token storage
    tokenStorage.storeToken(refreshToken, new TokenInfo(userId, expirationDate));

    return refreshToken;
  }

  public boolean validateRefreshToken(String refreshToken, String userId) {
    TokenInfo storedToken = tokenStorage.retrieveToken(refreshToken);

    if (storedToken != null) {
      Date currentDate = new Date();

      if (storedToken.getUserId().equals(userId) && storedToken.getExpirationDate().after(currentDate)) {
        // Valid refresh token
        return true;
      }
    }

    // Invalid or expired refresh token
    return false;
  }
}