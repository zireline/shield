package com.splitscale.shield;

import org.junit.jupiter.api.Test;

import com.splitscale.shield.refreshtoken.InMemoryTokenStorage;
import com.splitscale.shield.refreshtoken.RefreshTokenManager;
import com.splitscale.shield.refreshtoken.TokenStorage;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class RefreshTokenManagerTest {

  @Test
  void generateAndValidateRefreshToken() {
    // Example usage with InMemoryTokenStorage
    TokenStorage inMemoryTokenStorage = new InMemoryTokenStorage();
    RefreshTokenManager refreshTokenManager = new RefreshTokenManager(inMemoryTokenStorage);
    TimeUnit days = TimeUnit.DAYS;
    String uid = "some-id";

    assertDoesNotThrow(() -> {
      // Generate refresh token for user with ID "some-id"
      String generatedToken = refreshTokenManager.generateRefreshToken(uid, 30, days); // 24 hours
      System.out.println("Generated Refresh Token: " + generatedToken);

      // Validate the refresh token for user with ID uid
      boolean isValidToken = refreshTokenManager.validateRefreshToken(generatedToken, uid);
      assertTrue(isValidToken, "Refresh token should be valid");

      // Validate an invalid refresh token
      boolean isInvalidToken = refreshTokenManager.validateRefreshToken("invalid_token", uid);
      assertFalse(isInvalidToken, "Refresh token should be invalid");
    });
  }
}
