package com.splitscale.shield.response;

import com.splitscale.fordastore.core.user.UserResponse;

public class LoginResponse {
  UserResponse userResponse;
  String jws;

  public LoginResponse(UserResponse userResponse, String jws) {
    this.userResponse = userResponse;
    this.jws = jws;
  }

  public UserResponse getUserResponse() {
    return userResponse;
  }

  public void setUserResponse(UserResponse userResponse) {
    this.userResponse = userResponse;
  }

  public String getJws() {
    return jws;
  }

  public void setJws(String jws) {
    this.jws = jws;
  }

}
