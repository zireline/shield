package com.splitscale.shield.endpoints.validate;

public class ValidateRequest {
  String jwtToken;
  String userId;

  public ValidateRequest() {
    // default constructor
  }

  public ValidateRequest(String jwtToken, String userId) {
    this.jwtToken = jwtToken;
    this.userId = userId;
  }

  public String getJwtToken() {
    return jwtToken;
  }

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
