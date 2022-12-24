package com.splitscale.shield.endpoints;

import java.io.IOException;

import com.splitscale.fordastore.core.user.User;
import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.fordastore.core.user.register.RegisterInteractor;
import com.splitscale.shield.encryption.Encryptor;
import com.splitscale.shield.jwt.JwtInteractor;
import com.splitscale.shield.validator.UserRequestValidator;

public class RegisterEndpoint {
  RegisterInteractor registerInteractor;
  JwtInteractor jwtInteractor;

  public RegisterEndpoint(RegisterInteractor registerInteractor, JwtInteractor jwtInteractor) {
    this.registerInteractor = registerInteractor;
    this.jwtInteractor = jwtInteractor;
  }

  public String register(UserRequest userRequest) throws IllegalArgumentException, IOException {

    if (!UserRequestValidator.validate(userRequest)) {
      throw new IllegalArgumentException("Invalid username or password");
    }

    String hashedPassword = Encryptor.encrypt(userRequest.getPassword());
    userRequest.setPassword(hashedPassword);

    User user = registerInteractor.register(userRequest);

    return jwtInteractor.generateJwtFromUser(user);
  }
}