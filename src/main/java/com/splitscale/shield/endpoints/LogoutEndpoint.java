package com.splitscale.shield.endpoints;

import java.io.IOException;

import com.splitscale.shield.auth.AuthPublicKeyInteractor;

public class LogoutEndpoint {
  static AuthPublicKeyInteractor authPublicKeyInteractor;

  public void logout(String uid) throws IOException {
    authPublicKeyInteractor.deletePublicKeyByUID(uid);
  }
}


