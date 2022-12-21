package com.splitscale.shield.sanitizer;

import com.splitscale.fordastore.core.repositories.UserRepository;
import com.splitscale.fordastore.core.user.User;
import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.shield.Encryptor;

public class RegistrationSanitizer {
  UserRepository repository;
  Encryptor security;

  public RegistrationSanitizer(UserRepository repository, Encryptor security) {
    this.repository = repository;
    this.security = security;
  }

  public User sanitizeAndSave(UserRequest userRequest) throws Exception {

    // sanitizes the inputs
    // if (!isValidFormat(userRequest) && !isSanitized(userRequest)) {
    // throw new Exception("Invalid username or password");
    // }

    // success
    String hashedPassword = security.encrypt(userRequest.getPassword());
    userRequest.setPassword(hashedPassword);

    return repository.add(userRequest);
  }
}
