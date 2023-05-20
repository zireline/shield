package com.splitscale.shield.user.repository;

import com.splitscale.shield.user.User;

public interface UserRepository {
  public User getById(String id);
  public User[] getAll();
  public User getByUsername(String username);
  public String add(User user);
  public void update(User user);
  public void delete(String id);
}
