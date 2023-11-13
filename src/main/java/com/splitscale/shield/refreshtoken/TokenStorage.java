package com.splitscale.shield.refreshtoken;

public interface TokenStorage {
  void storeToken(String token, TokenInfo tokenInfo);

  TokenInfo retrieveToken(String token);
}
