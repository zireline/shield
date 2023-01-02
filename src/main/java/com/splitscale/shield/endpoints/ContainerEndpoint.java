package com.splitscale.shield.endpoints;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.fordastore.core.container.ContainerRequest;
import com.splitscale.fordastore.core.container.create.CreateContainerInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class ContainerEndpoint {
  private CreateContainerInteractor createContainerInteractor;

  public ContainerEndpoint(CreateContainerInteractor createContainerInteractor) {
    this.createContainerInteractor = createContainerInteractor;
  }

  public void create(ContainerRequest containerRequest, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    createContainerInteractor.createContainer(containerRequest);
  }
}