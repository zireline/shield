package com.splitscale.shield.auth;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class PublicKeyConverter {
  private PublicKeyConverter() {
    // default
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
