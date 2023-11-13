package com.splitscale.shield.repositories;

import java.io.IOException;
import java.util.List;

import com.splitscale.shield.credential.Credential;

public interface CredentialRepository {
  public Credential getById(String id) throws IOException;

  public Credential getByUserInfoId(String id) throws IOException;

  public List<Credential> getAll() throws IOException;

  public Credential getByUsername(String username) throws IOException, ObjectNotFoundException;

  public void add(Credential credential) throws IOException;

  public void update(Credential credential) throws IOException;

  public void delete(String id) throws IOException;

  public void deleteAll() throws IOException;
}
