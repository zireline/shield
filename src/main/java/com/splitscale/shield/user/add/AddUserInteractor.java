package com.splitscale.shield.user.add;

import com.splitscale.shield.user.User;
import com.splitscale.shield.user.repository.UserRepository;

public class AddUserInteractor {
  private UserRepository repository;

  public AddUserInteractor(UserRepository repository) {
    this.repository = repository;
  }

  public String add(User user) {
    return repository.add(user);
  }
}
