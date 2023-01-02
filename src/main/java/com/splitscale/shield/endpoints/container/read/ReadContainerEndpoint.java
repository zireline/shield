package com.splitscale.shield.endpoints.container.read;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.fordastore.core.container.read.ReadContainerInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class ReadContainerEndpoint {
  private ReadContainerInteractor readContainerInteractor;
  
  public ReadContainerEndpoint(ReadContainerInteractor readContainerInteractor) {
    this.readContainerInteractor = readContainerInteractor;
  }

  public void readListByUid(String uid, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    readContainerInteractor.readListByUid(uid);
  }

  public void readListByName(String name, String jwsToken) throws IOException, GeneralSecurityException {
    ShieldJws.validateJws(jwsToken);

    readContainerInteractor.readListByName(name);
  }

  public void readByContainerId(Long containerId, String jwsToken) throws IOException, GeneralSecurityException {
    ShieldJws.validateJws(jwsToken);

    readContainerInteractor.getByContainerId(containerId);
  }
}
