package com.splitscale.shield.endpoints;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.fordastore.core.url.UrlRequest;
import com.splitscale.fordastore.core.url.create.CreateUrlInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class URLEndpoint {
  private CreateUrlInteractor createUrlInteractor;

  public URLEndpoint(CreateUrlInteractor createUrlInteractor) {
    this.createUrlInteractor = createUrlInteractor;
  }

  public void create(UrlRequest urlRequest, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    createUrlInteractor.createContent(urlRequest);
  }
}