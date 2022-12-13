package com.splitscale.shield;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import com.splitscale.fordastore.core.security.Authorization;
import com.splitscale.fordastore.core.security.UserClaims;
import com.splitscale.fordastore.core.security.auth.AuthService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenizedAuthProvider {

  public UserClaims authorize(String token, String publicKey) {

    try {
      final PublicKey convertedPublicKey = base64ToPublicKey(publicKey);

      final Claims body = Jwts.parserBuilder()
          .setSigningKey(convertedPublicKey)
          .build()
          .parseClaimsJws(token)
          .getBody();

      UserClaims userClaims = new UserClaims();

      final Long id = Long.parseLong(body.get("sid", String.class));
      final String uid = body.getSubject();
      String username = body.getAudience();

      userClaims.setUsername(username);
      userClaims.setId(id);
      userClaims.setUid(uid);

      return userClaims;
    } catch (Exception e) {
      return null;
    }
  }

  public Authorization getAuthorization(UserClaims userClaims) {
    final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

    String jws = Jwts.builder()
        .setIssuer("splitscale")
        .setAudience(userClaims.getUsername())
        .claim("sid", Long.toString(userClaims.getId()))
        .setSubject(userClaims.getUid())
        .signWith(keyPair.getPrivate())
        .compact();

    return new Authorization(keyPair.getPublic(), jws);
  }

  public PublicKey base64ToPublicKey(String base64String) throws Exception {
    byte[] publicKeyBytes = Base64.getDecoder().decode(base64String);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    return keyFactory.generatePublic(keySpec);
  }

  public String publicKeyToBase64(PublicKey publicKey) {
    byte[] publicKeyBytes = publicKey.getEncoded();
    return Base64.getEncoder().encodeToString(publicKeyBytes);
  }

}
