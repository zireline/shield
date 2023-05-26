package com.splitscale.shield.repositories;

import java.io.IOException;
import java.util.List;

import com.splitscale.shield.credential.Credential;

public interface CredentialRepository {
  public Credential getById(String id) throws IOException;

  public List<Credential> getAll() throws IOException;

  public Credential getByUsername(String username) throws IOException, ObjectNotFoundException;

  public String add(Credential user) throws IOException;

  public void update(Credential user) throws IOException;

  public void delete(String id) throws IOException;

  public void deleteAll() throws IOException;
}
