package com.splitscale.shield.credential.update;

import java.io.IOException;

import com.splitscale.shield.credential.Credential;
import com.splitscale.shield.repositories.CredentialRepository;

public class UpdateCredentialUsernameInteractor {
  private CredentialRepository repository;

  public UpdateCredentialUsernameInteractor(CredentialRepository repository) {
    this.repository = repository;
  }

  public void updateUsername(String userInfoId, String newUsername) throws IOException {
    Credential credential = repository.getByUserInfoId(userInfoId);
    credential.setUsername(newUsername);
    repository.update(credential);
  }
}
