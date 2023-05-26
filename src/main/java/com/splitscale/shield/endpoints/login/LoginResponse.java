package com.splitscale.shield.endpoints.login;

import com.splitscale.shield.shielduser.ShieldUser;

public class LoginResponse {
  private String token;
  private ShieldUser user;

  public LoginResponse(String token, ShieldUser user) {
    this.token = token;
    this.user = user;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public ShieldUser getUser() {
    return user;
  }

  public void setUser(ShieldUser user) {
    this.user = user;
  }

}
