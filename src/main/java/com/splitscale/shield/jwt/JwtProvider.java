package com.splitscale.shield.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;

import com.splitscale.shield.jwt.validate.ValidJwtResponse;

public class JwtProvider {

  private String issuer = "Candace";

  public JwtProvider() {
    // default
  }

  public ValidJwtResponse validateJwt(String jwtToken, String id)
      throws GeneralSecurityException, IOException {
    SecretKey secretKey = SigningKeyWorkflow.getSigningKey();

    // minutes
    long minutesInSeconds = 100L * 60L;

    try {
      if (jwtToken.isEmpty()) {
        throw new IllegalArgumentException("Token is empty");
      }

      Claims claims = Jwts
          .parserBuilder()
          .setAllowedClockSkewSeconds(minutesInSeconds)
          .requireIssuer(issuer)
          .requireAudience(id)
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(jwtToken)
          .getBody();

      return new ValidJwtResponse(jwtToken, claims);

    } catch (IllegalArgumentException e) {
      throw new GeneralSecurityException(
          "Invalid authorization token: " + e.getMessage());
    } catch (ExpiredJwtException e) {
      throw new GeneralSecurityException("Token Expired: " + e.getMessage());
    } catch (JwtException e) {
      throw new GeneralSecurityException("Invalid JWT: " + e.getMessage());
    } catch (Exception e) {
      throw new GeneralSecurityException("Unexpected error: " + e.getMessage());
    }
  }

  public String invalidateJwt(String jwtToken) throws GeneralSecurityException {
    try {
      Claims claims = Jwts
          .parserBuilder()
          .setSigningKey(SigningKeyWorkflow.getSigningKey())
          .build()
          .parseClaimsJws(jwtToken)
          .getBody();

      // Check if the token is still valid and has not expired
      Date expirationDate = claims.getExpiration();
      if (expirationDate.before(new Date())) {
        throw new GeneralSecurityException("Token has already expired");
      }

      // Create a new JWT token with an expired expiration date
      Date currentDate = new Date();
      String id = claims.getId();
      String audience = claims.getAudience();
      SecretKey secretKey = SigningKeyWorkflow.getSigningKey();

      return Jwts
          .builder()
          .setIssuer(issuer)
          .setExpiration(currentDate) // Expired expiration date
          .setNotBefore(currentDate)
          .setIssuedAt(currentDate)
          .setAudience(audience)
          .signWith(secretKey)
          .setId(id)
          .compact();
    } catch (ExpiredJwtException e) {
      throw new GeneralSecurityException("Token has already expired");
    } catch (JwtException e) {
      throw new GeneralSecurityException("Invalid JWT: " + e.getMessage());
    } catch (Exception e) {
      throw new GeneralSecurityException("Unexpected error: " + e.getMessage());
    }
  }

  public String getJwt(String id) throws InvalidKeyException, IOException {
    Date currentDate = new Date();

    return Jwts
        .builder()
        .setIssuer(issuer)
        .setExpiration(new Date(System.currentTimeMillis() + 900000))
        .setNotBefore(currentDate)
        .setIssuedAt(currentDate)
        .setAudience(id)
        .signWith(SigningKeyWorkflow.getSigningKey())
        .setId("JWT-" + UUID.randomUUID().toString())
        .compact();
  }
}
