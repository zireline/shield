package com.splitscale.shield.refreshtoken;

import java.util.Date;

public class TokenInfo {
  private String userId;
  private Date expirationDate;

  public TokenInfo(String userId, Date expirationDate) {
    this.userId = userId;
    this.expirationDate = expirationDate;
  }

  public String getUserId() {
    return userId;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }
}