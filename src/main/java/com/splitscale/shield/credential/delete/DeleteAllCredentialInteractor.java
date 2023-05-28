package com.splitscale.shield.credential.delete;

import java.io.IOException;

import com.splitscale.shield.repositories.CredentialRepository;

public class DeleteAllCredentialInteractor {
  private CredentialRepository repository;

  public DeleteAllCredentialInteractor(CredentialRepository repository) {
    this.repository = repository;
  }

  void deleteAll() throws IOException {
    repository.deleteAll();
  }
}
