package com.splitscale.shield.endpoints.auth;

import java.io.IOException;

import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.fordastore.core.user.add.AddUserInteractor;
import com.splitscale.shield.password.PasswordHasher;
import com.splitscale.shield.validator.UserRequestValidator;

public class RegisterEndpoint {
  private AddUserInteractor addUserInteractor;

  public RegisterEndpoint(AddUserInteractor addUserInteractor) {
    this.addUserInteractor = addUserInteractor;
  }

  public void register(UserRequest userRequest) throws IllegalArgumentException, IOException {

    UserRequestValidator.validate(userRequest);

    String hashedPassword = PasswordHasher.encrypt(userRequest.getPassword());
    userRequest.setPassword(hashedPassword);

    addUserInteractor.add(userRequest);
  }
}