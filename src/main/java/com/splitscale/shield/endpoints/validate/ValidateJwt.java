package com.splitscale.shield.endpoints.validate;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.shield.jwt.JwtProvider;

public class ValidateJwt {
  JwtProvider provider;

  public ValidateJwt(JwtProvider provider) {
    this.provider = provider;
  }

  public ValidJwtResponse validate(String jwtToken, String id) throws GeneralSecurityException, IOException {
    return provider.validateJwt(jwtToken, id);
  }
}
