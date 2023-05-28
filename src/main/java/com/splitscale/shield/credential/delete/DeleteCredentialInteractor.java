package com.splitscale.shield.credential.delete;

import java.io.IOException;

import com.splitscale.shield.repositories.CredentialRepository;

public class DeleteCredentialInteractor {
  private CredentialRepository repository;

  public DeleteCredentialInteractor(CredentialRepository repository) {
    this.repository = repository;
  }

  void delete(String id) throws IOException {
    repository.delete(id);
  }
}
