package com.splitscale.shield.endpoints.url.delete;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.fordastore.core.url.delete.DeleteUrlInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class DeleteUrlEndpoint {
  private DeleteUrlInteractor deleteUrlInteractor;

  public DeleteUrlEndpoint(DeleteUrlInteractor deleteUrlInteractor) {
    this.deleteUrlInteractor = deleteUrlInteractor;
  }

  public void delete(Long id, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    deleteUrlInteractor.deleteContentById(id);
  }
}
