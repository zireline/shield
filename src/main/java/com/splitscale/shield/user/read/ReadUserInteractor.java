package com.splitscale.shield.user.read;

import com.splitscale.shield.user.User;
import com.splitscale.shield.user.repository.UserRepository;

public class ReadUserInteractor {
  private UserRepository repository;

  public ReadUserInteractor(UserRepository repository) {
    this.repository = repository;
  }

  public User getByUsername(String username) {
    return repository.getByUsername(username);
  }
}
