package com.splitscale.shield.endpoints.url.read;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.splitscale.fordastore.core.url.Url;
import com.splitscale.fordastore.core.url.UrlResponse;
import com.splitscale.fordastore.core.url.read.ReadUrlInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class ReadUrlEndpoint {
  private ReadUrlInteractor readUrlInteractor;

  public ReadUrlEndpoint(ReadUrlInteractor readUrlInteractor) {
    this.readUrlInteractor = readUrlInteractor;
  }

  public List<UrlResponse> getALLByContainerID(Long containerId, String jwsToken)
      throws IOException, GeneralSecurityException {
    ShieldJws.validateJws(jwsToken);

    List<UrlResponse> responses = new ArrayList<UrlResponse>();
    List<Url> urls = readUrlInteractor.getALLByContainerID(containerId);

    for (Url url : urls) {
      UrlResponse urlResponse = new UrlResponse();
      urlResponse.setUrlID(url.getUrlID());
      urlResponse.setInnerUrl(url.getInnerUrl());
      responses.add(urlResponse);
    }

    return responses;

  }

  public Url getByUrlID(Long urlId, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    return readUrlInteractor.getByUrlID(urlId);
  }
}
