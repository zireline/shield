package com.splitscale.shield;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.crypto.SecretKey;

import org.junit.Test;

import com.splitscale.shield.jws.ShieldJws;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class JwsTest {

  @Test
  public void shouldValidateToken() {
    String jwsToken = ShieldJws.getJws();

    assertNotNull(jwsToken);

    System.out.println("JWS TOKEN: " + jwsToken);

    assertDoesNotThrow(() -> ShieldJws.validateJws(jwsToken));
  }

  @Test
  public void shouldAuthorize() {
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // or HS384 or HS512
    String secretString = Encoders.BASE64.encode(key.getEncoded());

    assertNotNull(secretString);

    System.out.println("secretString: " + secretString);
  }

}
