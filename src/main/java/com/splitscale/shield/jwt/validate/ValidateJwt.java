package com.splitscale.shield.jwt.validate;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.shield.jwt.JwtProvider;

public class ValidateJwt {
  JwtProvider provider;

  public ValidateJwt(JwtProvider provider) {
    this.provider = provider;
  }

  public void validate(String jwtToken, String id) throws GeneralSecurityException, IOException {
    provider.validateJwt(jwtToken, id);
  }
}
