package com.splitscale.shield.auth;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

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

  public static User authorize(String token, String publicKey) {

    try {
      final PublicKey convertedPublicKey = base64ToPublicKey(publicKey);

      final Claims body = Jwts.parserBuilder()
          .setSigningKey(convertedPublicKey)
          .build()
          .parseClaimsJws(token)
          .getBody();

      User user = new User();

      final String uid = body.getSubject();
      String username = body.getAudience();

      user.setUsername(username);
      user.setUid(uid);

      return user;
    } catch (Exception e) {
      return null;
    }
  }

  public static Authorization getAuthorization(User user) {
    final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

    String jws = Jwts.builder()
        .setIssuer("splitscale")
        .setAudience(user.getUsername())
        .setSubject(user.getUid())
        .signWith(keyPair.getPrivate())
        .compact();

    return new Authorization(keyPair.getPublic(), jws);
  }

  public static PublicKey base64ToPublicKey(String base64String) throws Exception {
    byte[] publicKeyBytes = Base64.getDecoder().decode(base64String);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePublic(keySpec);
  }

  public static String publicKeyToBase64(PublicKey publicKey) {
    byte[] publicKeyBytes = publicKey.getEncoded();
    return Base64.getEncoder().encodeToString(publicKeyBytes);
  }

}
