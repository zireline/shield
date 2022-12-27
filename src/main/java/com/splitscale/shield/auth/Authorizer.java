package com.splitscale.shield.auth;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Date;
import java.util.UUID;

import com.splitscale.fordastore.core.auth.Authorization;
import com.splitscale.fordastore.core.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class Authorizer {

  private Authorizer() {
    // default implementation
  }

  public static User authorize(String token, String publicKey) throws GeneralSecurityException {

    try {
      final PublicKey convertedPublicKey = PublicKeyConverter.base64ToPublicKey(publicKey);

      final Claims body = Jwts.parserBuilder()
          .requireIssuer("splitscale.com")
          .require("permission","user")
          .setSigningKey(convertedPublicKey)
          .build()
          .parseClaimsJws(token)
          .getBody();

      User user = new User();

      final String uid = body.getSubject();
      String username = body.getAudience();

      Date expiration = body.getExpiration();
      if (expiration.before(new Date())) {
        throw new Exception();
      }

      Date notBefore = body.getNotBefore();
      if (notBefore.after(new Date())) {
        throw new Exception();
      }
      user.setUsername(username);
      user.setUid(uid);

      return user;
    } catch (Exception e) {
      throw new GeneralSecurityException("Invalid authorization token");
    }
  }

  public static Authorization getAuthorization(User user) {
    final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

    String jws = Jwts.builder()
        .setIssuer("splitscale.com")
        .setSubject(user.getUid())
        .claim("permission", "user")
        .setExpiration(new Date(System.currentTimeMillis() + 900000))
        .setNotBefore(new Date())
        .setIssuedAt(new Date())
        .setAudience(user.getUsername())
        .signWith(keyPair.getPrivate())
        .setId(UUID.randomUUID().toString())
        .compact();

    return new Authorization(keyPair.getPublic(), jws);
  }
}
