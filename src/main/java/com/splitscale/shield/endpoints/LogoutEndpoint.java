package com.splitscale.shield.endpoints;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.shield.auth.AuthPublicKeyInteractor;
import com.splitscale.shield.auth.Authorizer;

public class LogoutEndpoint {
  public AuthPublicKeyInteractor authPublicKeyInteractor;

  public LogoutEndpoint(AuthPublicKeyInteractor authPublicKeyInteractor) {
    this.authPublicKeyInteractor = authPublicKeyInteractor;
  }

  public void logout(String jwtToken, String uid) throws IOException, GeneralSecurityException {
    String pk = authPublicKeyInteractor.getPublicKeyByUID(uid);

    Authorizer.validateToken(jwtToken, pk);

    authPublicKeyInteractor.deletePublicKeyByUID(uid);
  }
}
