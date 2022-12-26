package com.splitscale.shield.endpoints;

import java.io.IOException;

import com.splitscale.fordastore.core.user.User;
import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.fordastore.core.user.login.LoginInteractor;
import com.splitscale.shield.encryption.Encryptor;
import com.splitscale.shield.jwt.JwtInteractor;
import com.splitscale.shield.validator.UserRequestValidator;

public class LoginEndpoint {
  LoginInteractor loginInteractor;
  JwtInteractor jwtInteractor;

  public LoginEndpoint(LoginInteractor loginInteractor, JwtInteractor jwtInteractor) {
    this.loginInteractor = loginInteractor;
    this.jwtInteractor = jwtInteractor;
  }

  public String login(UserRequest userRequest) throws IllegalArgumentException, IOException {

    UserRequestValidator.validate(userRequest);

    User user = loginInteractor.login(userRequest);

    boolean isRegistered = Encryptor.verify(userRequest.getPassword(), user.getPassword());

    if (!isRegistered) {
      throw new IllegalArgumentException("User not registered");
    }

    return jwtInteractor.generateJwtFromUser(user);
  }
}
