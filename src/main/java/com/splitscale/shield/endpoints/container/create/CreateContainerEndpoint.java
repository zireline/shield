package com.splitscale.shield.endpoints.container.create;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.fordastore.core.container.ContainerRequest;
import com.splitscale.fordastore.core.container.create.CreateContainerInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class CreateContainerEndpoint {
  private CreateContainerInteractor createContainerInteractor;

  public CreateContainerEndpoint(CreateContainerInteractor createContainerInteractor) {
    this.createContainerInteractor = createContainerInteractor;
  }

  public void create(ContainerRequest containerRequest, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    createContainerInteractor.createContainer(containerRequest);
  }
}