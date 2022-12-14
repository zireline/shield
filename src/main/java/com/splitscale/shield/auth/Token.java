package com.splitscale.shield.auth;

public class Token {
  private String uid;
  private String publicKey;

  public Token(String uid, String publicKey) {
    this.uid = uid;
    this.publicKey = publicKey;
  }

  public String getUid() {
    return uid;
  }

  public String getPublicKey() {
    return publicKey;
  }

}
