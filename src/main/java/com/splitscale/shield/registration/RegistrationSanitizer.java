package com.splitscale.shield.registration;

import com.splitscale.fordastore.core.repositories.UserRepository;
import com.splitscale.fordastore.core.user.User;
import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.shield.Encryptor;

public class RegistrationSanitizer {
  UserRepository repository;
  Encryptor security;
  UsernameValidator usernameValidator;
  PasswordValidator passwordValidator;

  public RegistrationSanitizer(UserRepository repository, Encryptor security, UsernameValidator usernameValidator,
      PasswordValidator passwordValidator) {
    this.repository = repository;
    this.security = security;
    this.usernameValidator = usernameValidator;
    this.passwordValidator = passwordValidator;
  }

  public User sanitizeAndSave(UserRequest userRequest) throws Exception {

    // sanitizes the inputs
    if (!usernameValidator.isValid(userRequest.getUsername())
        || !passwordValidator.isValid(userRequest.getPassword())) {
      throw new Exception("Invalid username or password");
    }

    // success
    String hashedPassword = security.encrypt(userRequest.getPassword());
    userRequest.setPassword(hashedPassword);

    return repository.add(userRequest);
  }
}
