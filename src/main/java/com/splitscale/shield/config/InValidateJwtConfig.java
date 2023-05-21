package com.splitscale.shield.config;

import com.splitscale.shield.jwt.JwtProvider;
import com.splitscale.shield.invalidate.InvalidateJwt;

public class InValidateJwtConfig {

  private InValidateJwtConfig() {
    // default
  }

  public static InvalidateJwt getInValidateJwt() {
    JwtProvider provider = JwtConfig.getJwtProvider();
    return new InvalidateJwt(provider);
  }
}
