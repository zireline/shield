package com.splitscale.shield.auth;

import java.security.PublicKey;

public class Authorization {
  private PublicKey publicKey;
  private String token;

  public Authorization(PublicKey publicKey, String token) {
    this.publicKey = publicKey;
    this.token = token;
  }

  public PublicKey getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(PublicKey publicKey) {
    this.publicKey = publicKey;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

}
