package com.splitscale.shield.config;

import com.splitscale.shield.jwt.JwtProvider;
import com.splitscale.shield.validate.ValidateJwt;

public class ValidateJwtConfig {

  private ValidateJwtConfig() {
    // default
  }

  public static ValidateJwt getValidateJwt() {
    JwtProvider provider = JwtConfig.getJwtProvider();
    return new ValidateJwt(provider);
  }
}
