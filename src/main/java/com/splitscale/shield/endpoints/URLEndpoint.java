package com.splitscale.shield.endpoints;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.fordastore.core.url.UrlRequest;
import com.splitscale.fordastore.core.url.create.CreateUrlInteractor;
import com.splitscale.shield.auth.AuthPublicKeyInteractor;
import com.splitscale.shield.auth.Authorizer;


public class URLEndpoint {
  private CreateUrlInteractor createUrlInteractor;
  private AuthPublicKeyInteractor authPublicKeyInteractor;
  
  public URLEndpoint(CreateUrlInteractor createUrlInteractor, AuthPublicKeyInteractor authPublicKeyInteractor) {
    this.createUrlInteractor = createUrlInteractor;
    this.authPublicKeyInteractor = authPublicKeyInteractor;
  }

  public void create(UrlRequest urlRequest, String jwtToken, String uid) throws IOException, GeneralSecurityException {
    String pk = authPublicKeyInteractor.getPublicKeyByUID(uid);

    Authorizer.validateToken(jwtToken, pk);

    createUrlInteractor.createContent(urlRequest);
  }
}