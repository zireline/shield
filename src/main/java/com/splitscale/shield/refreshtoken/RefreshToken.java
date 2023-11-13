package com.splitscale.shield.refreshtoken;

public class RefreshToken {
  private String token;
  private TokenInfo tokenInfo;

  public RefreshToken(String token, TokenInfo tokenInfo) {
    this.token = token;
    this.tokenInfo = tokenInfo;
  }

  public String getId() {
    return token;
  }

  public TokenInfo getTokenInfo() {
    return tokenInfo;
  }

}
