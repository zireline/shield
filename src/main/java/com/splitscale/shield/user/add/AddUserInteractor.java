package com.splitscale.shield.user.add;

import java.io.IOException;

import com.splitscale.shield.user.User;
import com.splitscale.shield.user.repository.UserRepository;

public class AddUserInteractor {
  private UserRepository repository;

  public AddUserInteractor(UserRepository repository) {
    this.repository = repository;
  }

  public String add(User user) throws IOException {
    return repository.add(user);
  }
}
