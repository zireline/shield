package com.splitscale.shield.config;

import com.splitscale.shield.jwt.JwtProvider;
import com.splitscale.shield.jwt.get.GetJwt;

public class JwtConfig {

  private JwtConfig() {
    // default
  }

  public static GetJwt getJwt(JwtProvider provider) {
    return new GetJwt(provider);
  }

  public static JwtProvider getJwtProvider() {
    return new JwtProvider();
  }
}
