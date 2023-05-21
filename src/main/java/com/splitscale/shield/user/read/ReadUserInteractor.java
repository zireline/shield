package com.splitscale.shield.user.read;

import java.io.IOException;

import com.splitscale.shield.user.User;
import com.splitscale.shield.user.repository.ObjectNotFoundException;
import com.splitscale.shield.user.repository.UserRepository;

public class ReadUserInteractor {
  private UserRepository repository;

  public ReadUserInteractor(UserRepository repository) {
    this.repository = repository;
  }

  public User getByUsername(String username) throws IOException {
    try {
      return repository.getByUsername(username);
    } catch (ObjectNotFoundException e) {
      return new User(null, null, null);
    }
  }
}
