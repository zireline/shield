package com.splitscale.shield.endpoints.container.edit;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.fordastore.core.container.Container;
import com.splitscale.fordastore.core.container.edit.EditContainerInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class EditContainerEndpoint {
  private EditContainerInteractor editContainerInteractor;
  
  public EditContainerEndpoint(EditContainerInteractor editContainerInteractor) {
    this.editContainerInteractor = editContainerInteractor;
  }

  public void edit(Container container, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    editContainerInteractor.editContainer(container);
  }
}