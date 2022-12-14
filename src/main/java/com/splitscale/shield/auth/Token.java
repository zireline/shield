package com.splitscale.shield.auth;

public class Token {
  private Long id;
  private String uid;
  private String publicKey;

  public Token(Long id, String uid, String publicKey) {
    this.id = id;
    this.uid = uid;
    this.publicKey = publicKey;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }
}
