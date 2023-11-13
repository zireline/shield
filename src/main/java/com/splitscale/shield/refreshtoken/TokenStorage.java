package com.splitscale.shield.refreshtoken;

import java.io.IOException;

public interface TokenStorage {
  void storeToken(String token, TokenInfo tokenInfo) throws IOException;

  TokenInfo retrieveToken(String token) throws IOException;
}
