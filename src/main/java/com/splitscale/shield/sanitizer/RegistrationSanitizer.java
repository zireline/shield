package com.splitscale.shield.sanitizer;

import com.splitscale.fordastore.core.repositories.UserRepository;
import com.splitscale.fordastore.core.user.User;
import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.shield.Encryptor;

public class RegistrationSanitizer {
  UserRepository repository;
  Encryptor security;
  UsernameValidator username;

  public RegistrationSanitizer(UserRepository repository, Encryptor security, UsernameValidator username) {
    this.repository = repository;
    this.security = security;
    this.username = username;
  }

  public User sanitizeAndSave(UserRequest userRequest) throws Exception {

    // sanitizes the inputs
    if (!username.isValid(userRequest.getUsername())) {
      throw new Exception("Invalid username or password");
    }

    // success
    String hashedPassword = security.encrypt(userRequest.getPassword());
    userRequest.setPassword(hashedPassword);

    return repository.add(userRequest);
  }
}
