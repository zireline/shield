package com.splitscale.shield.credential.read;

import java.io.IOException;
import java.util.List;

import com.splitscale.shield.credential.Credential;
import com.splitscale.shield.repositories.CredentialRepository;

public class ReadAllCredentialInteractor {
  private CredentialRepository repository;

  public ReadAllCredentialInteractor(CredentialRepository repository) {
    this.repository = repository;
  }

  public List<Credential> getAllCredentials() throws IOException {
    return repository.getAll();
  }
}
