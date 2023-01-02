package com.splitscale.shield.jws;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.UUID;

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

    try {
      final Claims body = Jwts.parserBuilder()
          .requireIssuer(jwsConfig.getIssuer())
          .setSigningKey(jwsConfig.getSigningKey())
          .build()
          .parseClaimsJws(jws)
          .getBody();

      boolean expiration = body.getExpiration().before(currentDate);
      boolean notBefore = body.getNotBefore().after(currentDate);

      if (notBefore || expiration) {
        throw new Exception();
      }

    } catch (Exception e) {
      throw new GeneralSecurityException("Invalid authorization token");
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
