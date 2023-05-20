package com.splitscale.shield.jwt.invalidate;

import java.security.GeneralSecurityException;

import com.splitscale.shield.jwt.JwtProvider;

public class InvalidateJwt {
  JwtProvider provider;

  public InvalidateJwt(JwtProvider provider) {
    this.provider = provider;
  }

  public String invalidate(String jwtToken) throws GeneralSecurityException {
    return provider.invalidateJwt(jwtToken);
  }
}
