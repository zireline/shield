package com.splitscale.shield.endpoints.url.edit;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.splitscale.fordastore.core.url.Url;
import com.splitscale.fordastore.core.url.edit.EditUrlInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class EditUrlEndpoint {
  private EditUrlInteractor editUrlInteractor;

  public EditUrlEndpoint(EditUrlInteractor editUrlInteractor) {
    this.editUrlInteractor = editUrlInteractor;
  }

  public void edit(Url url, String jwsToken) throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    editUrlInteractor.updateUrl(url);
  }
}
