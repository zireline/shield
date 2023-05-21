package com.splitscale.shield.user.repository;

import java.io.IOException;

import com.splitscale.shield.user.User;

public interface UserRepository {
  public User getById(String id) throws IOException;

  public User[] getAll() throws IOException;

  public User getByUsername(String username) throws IOException, ObjectNotFoundException;

  public String add(User user) throws IOException;

  public void update(User user) throws IOException;

  public void delete(String id) throws IOException;
}
