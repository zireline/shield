package com.splitscale.shield.repositories.impl;

import java.io.IOException;

import com.splitscale.shield.io.Storage;
import com.splitscale.shield.refreshtoken.RefreshToken;
import com.splitscale.shield.refreshtoken.TokenInfo;
import com.splitscale.shield.refreshtoken.TokenStorage;

public class TokenStorageImpl implements TokenStorage {
  private final Storage<RefreshToken> store;

  public TokenStorageImpl(Storage<RefreshToken> store) {
    this.store = store;
  }

  @Override
  public void storeToken(String token, TokenInfo tokenInfo) throws IOException {
    RefreshToken refreshToken = new RefreshToken(token, tokenInfo);
    store.create(refreshToken);
  }

  @Override
  public TokenInfo retrieveToken(String token) throws IOException {
    RefreshToken refreshToken = store.getByKeyValue("token", token);
    return refreshToken.getTokenInfo();
  }

}
