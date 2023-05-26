package com.splitscale.shield.credential.read;

import java.io.IOException;

import com.splitscale.shield.credential.Credential;
import com.splitscale.shield.repositories.CredentialRepository;
import com.splitscale.shield.repositories.ObjectNotFoundException;

public class ReadCredentialInteractor {
  private CredentialRepository repository;

  public ReadCredentialInteractor(CredentialRepository repository) {
    this.repository = repository;
  }

  public Credential getByUsername(String username) throws IOException {
    try {
      return repository.getByUsername(username);
    } catch (ObjectNotFoundException e) {
      return null;
    }
  }
}
