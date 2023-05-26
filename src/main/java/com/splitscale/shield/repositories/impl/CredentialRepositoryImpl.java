package com.splitscale.shield.repositories.impl;

import java.io.IOException;
import java.util.List;

import com.splitscale.shield.credential.Credential;
import com.splitscale.shield.io.CredentialFileManager;
import com.splitscale.shield.repositories.CredentialRepository;
import com.splitscale.shield.repositories.ObjectNotFoundException;

public class CredentialRepositoryImpl implements CredentialRepository {
  private final CredentialFileManager fileManager;

  public CredentialRepositoryImpl(CredentialFileManager fileManager) {
    this.fileManager = fileManager;
  }

  @Override
  public Credential getById(String id) throws IOException {
    return fileManager.read(id);
  }

  @Override
  public List<Credential> getAll() throws IOException {
    return fileManager.getAll();
  }

  @Override
  public Credential getByUsername(String username) throws IOException, ObjectNotFoundException {

    List<Credential> credentialList = fileManager.getAll();

    for (Credential credential : credentialList) {
      if (username.equals(credential.getUsername())) {
        return credential;
      }
    }

    throw new ObjectNotFoundException("User with Username: " + username + " not found");
  }

  @Override
  public String add(Credential credential) throws IOException {
    return fileManager.create(credential);
  }

  @Override
  public void update(Credential credential) throws IOException {
    fileManager.update(credential);
  }

  @Override
  public void delete(String id) throws IOException {
    fileManager.delete(id);
  }

  @Override
  public void deleteAll() throws IOException {
    fileManager.deleteAll();
  }
}
