package com.splitscale.shield.endpoints.container.read;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.splitscale.fordastore.core.container.Container;
import com.splitscale.fordastore.core.container.read.ReadContainerInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class ReadContainerEndpoint {
  private ReadContainerInteractor readContainerInteractor;
  
  public ReadContainerEndpoint(ReadContainerInteractor readContainerInteractor) {
    this.readContainerInteractor = readContainerInteractor;
  }

  public List<Container> readListByUid(String uid, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    return readContainerInteractor.readListByUid(uid);
  }

  public List<Container> readListByName(String name, String jwsToken) throws IOException, GeneralSecurityException {
    ShieldJws.validateJws(jwsToken);

    return readContainerInteractor.readListByName(name);
  }

  public Container readByContainerId(Long containerId, String jwsToken) throws IOException, GeneralSecurityException {
    ShieldJws.validateJws(jwsToken);

    return readContainerInteractor.getByContainerId(containerId);
  }
}
