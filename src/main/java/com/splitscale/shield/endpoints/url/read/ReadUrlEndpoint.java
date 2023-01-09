package com.splitscale.shield.endpoints.url.read;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.splitscale.fordastore.core.url.Url;
import com.splitscale.fordastore.core.url.read.ReadUrlInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class ReadUrlEndpoint {
  private ReadUrlInteractor readUrlInteractor;

  public ReadUrlEndpoint(ReadUrlInteractor readUrlInteractor) {
    this.readUrlInteractor = readUrlInteractor;
  }

  public List<Url> getALLByContainerID(Long containerId, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    return readUrlInteractor.getALLByContainerID(containerId);
  }

  public Url getByUrlID(Long urlId, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    return readUrlInteractor.getByUrlID(urlId);
  }
}
