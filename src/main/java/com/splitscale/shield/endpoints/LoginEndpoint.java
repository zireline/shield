package com.splitscale.shield.endpoints;

import java.io.IOException;

import com.splitscale.fordastore.core.user.User;
import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.fordastore.core.user.login.LoginInteractor;
import com.splitscale.shield.auth.AuthPublicKeyInteractor;
import com.splitscale.shield.encryption.Encryptor;
import com.splitscale.shield.validator.UserRequestValidator;

public class LoginEndpoint {
  private LoginInteractor loginInteractor;
  private AuthPublicKeyInteractor authPublicKeyInteractor;

  private User user;

  public LoginEndpoint(LoginInteractor loginInteractor, AuthPublicKeyInteractor authPublicKeyInteractor) {
    this.loginInteractor = loginInteractor;
    this.authPublicKeyInteractor = authPublicKeyInteractor;
  }

  public String login(UserRequest userRequest) throws IllegalArgumentException, IOException {

    UserRequestValidator.validate(userRequest);

    // TODO: user not registered is username is not found
    user = loginInteractor.login(userRequest);

    boolean isRegistered = Encryptor.verify(userRequest.getPassword(), user.getPassword());

    if (!isRegistered) {
      throw new IllegalArgumentException("Wrong username or password");
    }

    return authPublicKeyInteractor.generateJwtFromUser(user);
  }

  public String getUid() {
    return user.getUid();
  }
}
