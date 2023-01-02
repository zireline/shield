package com.splitscale.shield.endpoints;

import java.io.IOException;

import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.fordastore.core.user.register.RegisterInteractor;
import com.splitscale.shield.password.PasswordHasher;
import com.splitscale.shield.validator.UserRequestValidator;

public class RegisterEndpoint {
  private RegisterInteractor registerInteractor;

  public RegisterEndpoint(RegisterInteractor registerInteractor) {
    this.registerInteractor = registerInteractor;
  }

  public void register(UserRequest userRequest) throws IllegalArgumentException, IOException {

    UserRequestValidator.validate(userRequest);

    String hashedPassword = PasswordHasher.encrypt(userRequest.getPassword());
    userRequest.setPassword(hashedPassword);

    registerInteractor.register(userRequest);
  }
}