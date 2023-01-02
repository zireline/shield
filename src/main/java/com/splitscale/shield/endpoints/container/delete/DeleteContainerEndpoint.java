package com.splitscale.shield.endpoints.container.delete;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.fordastore.core.container.ContainerRequest;
import com.splitscale.fordastore.core.container.delete.DeleteContainerInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class DeleteContainerEndpoint {
  private DeleteContainerInteractor deleteContainerInteractor;

  public DeleteContainerEndpoint(DeleteContainerInteractor deleteContainerInteractor) {
    this.deleteContainerInteractor = deleteContainerInteractor;
  }

  public void delete(Long containerId, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    deleteContainerInteractor.deleteContainer(containerId);
  }
}

