package com.splitscale.shield.endpoints.url.create;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.fordastore.core.url.Url;
import com.splitscale.fordastore.core.url.UrlRequest;
import com.splitscale.fordastore.core.url.UrlResponse;
import com.splitscale.fordastore.core.url.create.CreateUrlInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class CreateUrlEndpoint {
  private CreateUrlInteractor createUrlInteractor;

  public CreateUrlEndpoint(CreateUrlInteractor createUrlInteractor) {
    this.createUrlInteractor = createUrlInteractor;
  }

  public Url create(UrlRequest urlRequest, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    return createUrlInteractor.createUrl(urlRequest);
  }
}