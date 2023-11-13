package com.splitscale.shield.endpoints.login;

import com.splitscale.shield.shielduser.ShieldUser;

public class LoginResponse {
  private Tokens tokens;
  private ShieldUser user;

  public LoginResponse(Tokens tokens, ShieldUser user) {
    this.tokens = tokens;
    this.user = user;
  }

  public Tokens getTokens() {
    return tokens;
  }

  public ShieldUser getUser() {
    return user;
  }

}
