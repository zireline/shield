package com.splitscale.shield;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.splitscale.shield.jwt.JwtProvider;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class JwtProviderTest {

  @Test
  public void testJwtOperations() {
    JwtProvider provider = new JwtProvider();
    String id = "exampleId";
    String jwtToken;

    try {
      // Generate JWT
      jwtToken = provider.getJwt(id);
      Assertions.assertNotNull(jwtToken);

      // Validate JWT
      provider.validateJwt(jwtToken, id);

      // Invalidate JWS
      provider.invalidateJwt(jwtToken);
    } catch (GeneralSecurityException | IOException e) {
      Assertions.fail("Exception occurred: " + e.getMessage());
    }
  }
}
