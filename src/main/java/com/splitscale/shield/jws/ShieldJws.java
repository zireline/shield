package com.splitscale.shield.jws;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import com.splitscale.shield.jws.config.JwsProps;
import com.splitscale.shield.jws.config.JwsPropsLoader;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ShieldJws {
  private static JwsProps jwsConfig = JwsPropsLoader.loadProps();

  private ShieldJws() {
    // default
  }

  public static void validateJws(String jws) throws GeneralSecurityException {
    Date currentDate = new Date();
    String issuer = jwsConfig.getIssuer();
    SecretKey secretKey = jwsConfig.getSigningKey();

    System.out.println("[ShieldJws] Issuer: " + issuer);
    System.out.println("[ShieldJws] SigningKey: " + secretKey.getAlgorithm());
    System.out.println("[ShieldJws] JWS TOKEN: " + jws);

    try {
      // final Claims body = Jwts.parserBuilder()
      // .requireIssuer(issuer)
      // .setSigningKey(secretKey)
      // .build()
      // .parseClaimsJws(jws)
      // .getBody();

      // if (body.getExpiration().before(currentDate)) {
      // throw new Exception("Expired token");
      // }

      // if (body.getNotBefore().after(currentDate)) {
      // throw new Exception("Old token");
      // }

      if (jws.isEmpty()) {
        throw new Exception("Token is empty");
      }

    } catch (IllegalArgumentException e) {
      System.out.println("[ShieldJws] Error: " + e.getMessage());

      throw new GeneralSecurityException("Invalid authorization token: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("[ShieldJws] Error: " + e.getMessage());

      throw new GeneralSecurityException("Token Expired or old: " + e.getMessage());
    }
  }

  public static String getJws() {
    Date currentDate = new Date();

    return Jwts.builder()
        .setIssuer(jwsConfig.getIssuer())
        .setExpiration(new Date(System.currentTimeMillis() + 900000))
        .setNotBefore(currentDate)
        .setIssuedAt(currentDate)
        .setAudience(jwsConfig.getAudience())
        .signWith(jwsConfig.getSigningKey())
        .setId(UUID.randomUUID().toString())
        .compact();
  }
}
