package com.splitscale.shield.endpoints.refresh;

public class RefreshRequest {
  private String userId;
  private String refreshToken;

  public RefreshRequest() {
    // default
  }

  public RefreshRequest(String userId, String refreshToken) {
    this.userId = userId;
    this.refreshToken = refreshToken;
  }

  public String getUserId() {
    return userId;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

}
