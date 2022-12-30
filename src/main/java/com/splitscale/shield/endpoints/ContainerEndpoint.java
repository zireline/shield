package com.splitscale.shield.endpoints;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.fordastore.core.container.ContainerRequest;
import com.splitscale.fordastore.core.container.create.CreateContainerInteractor;
import com.splitscale.shield.auth.AuthPublicKeyInteractor;
import com.splitscale.shield.auth.Authorizer;

public class ContainerEndpoint {
  private CreateContainerInteractor createContainerInteractor;
  private AuthPublicKeyInteractor authPublicKeyInteractor;

  public ContainerEndpoint(CreateContainerInteractor createContainerInteractor, AuthPublicKeyInteractor authPublicKeyInteractor) {
    this.createContainerInteractor = createContainerInteractor;
    this.authPublicKeyInteractor = authPublicKeyInteractor;
  }

  public void create(ContainerRequest containerRequest, String jwtToken) throws IOException, GeneralSecurityException {
    String uid = containerRequest.getUid();
    String pk = authPublicKeyInteractor.getPublicKeyByUID(uid);

    Authorizer.validateToken(jwtToken, pk);

    createContainerInteractor.createContainer(containerRequest);
  }
}