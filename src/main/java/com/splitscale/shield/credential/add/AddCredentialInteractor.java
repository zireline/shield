package com.splitscale.shield.credential.add;

import java.io.IOException;

import com.splitscale.shield.credential.Credential;
import com.splitscale.shield.repositories.CredentialRepository;

public class AddCredentialInteractor {
  private CredentialRepository repository;

  public AddCredentialInteractor(CredentialRepository repository) {
    this.repository = repository;
  }

  public String add(Credential credential) throws IOException {
    return repository.add(credential);
  }
}
