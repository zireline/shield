package com.splitscale.shield.endpoints;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.fordastore.core.container.Container;
import com.splitscale.fordastore.core.container.ContainerRequest;
import com.splitscale.fordastore.core.container.create.CreateContainerInteractor;
import com.splitscale.shield.auth.Authorizer;
import com.splitscale.shield.jwt.JwtInteractor;

public class ContainerEndpoint {
  private CreateContainerInteractor createContainerInteractor;
  private JwtInteractor jwtInteractor;

  public ContainerEndpoint(CreateContainerInteractor createContainerInteractor, JwtInteractor jwtInteractor) {
    this.createContainerInteractor = createContainerInteractor;
    this.jwtInteractor = jwtInteractor;
  }

  public Container create(ContainerRequest containerRequest, String jwtToken)
      throws IOException, GeneralSecurityException {
    String uid = containerRequest.getUid();
    String pk = jwtInteractor.getPublicKeyByUID(uid);

    Authorizer.validateToken(jwtToken, pk);

    return createContainerInteractor.createContainer(containerRequest);
  }
}