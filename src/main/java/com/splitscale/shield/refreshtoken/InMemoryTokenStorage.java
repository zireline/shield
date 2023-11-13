package com.splitscale.shield.refreshtoken;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTokenStorage implements TokenStorage {
  private Map<String, TokenInfo> tokens = new HashMap<>();

  @Override
  public void storeToken(String token, TokenInfo tokenInfo) {
    tokens.put(token, tokenInfo);
    displayAllTokens();
  }

  @Override
  public TokenInfo retrieveToken(String token) {
    return tokens.get(token);
  }

  public void displayAllTokens() {
    System.out.println("Contents of the tokens map:");
    for (Map.Entry<String, TokenInfo> entry : tokens.entrySet()) {
      String token = entry.getKey();
      TokenInfo tokenInfo = entry.getValue();
      System.out.println(
          "Token: " + token + ", User ID: " + tokenInfo.getUserId() + ", Expires At: "
              + tokenInfo.getExpirationDate().toString());
    }
  }
}
