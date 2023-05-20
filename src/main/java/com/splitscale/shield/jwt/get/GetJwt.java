package com.splitscale.shield.jwt.get;

import java.io.IOException;

import com.splitscale.shield.jwt.JwtProvider;

import io.jsonwebtoken.security.InvalidKeyException;

public class GetJwt {
  JwtProvider provider;

  public GetJwt(JwtProvider provider) {
    this.provider = provider;
  }

  public String get(String id) throws InvalidKeyException, IOException {
    return provider.getJwt(id);
  }
}
