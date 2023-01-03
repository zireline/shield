package com.splitscale.shield.endpoints.auth;

import java.io.IOException;

import com.splitscale.fordastore.core.user.User;
import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.fordastore.core.user.UserResponse;
import com.splitscale.fordastore.core.user.get.GetUserInteractor;
import com.splitscale.shield.jws.ShieldJws;
import com.splitscale.shield.password.PasswordHasher;
import com.splitscale.shield.response.LoginResponse;
import com.splitscale.shield.validator.UserRequestValidator;

public class LoginEndpoint {
  private GetUserInteractor loginInteractor;

  public LoginEndpoint(GetUserInteractor loginInteractor) {
    this.loginInteractor = loginInteractor;
  }

  public LoginResponse login(UserRequest userRequest)
      throws IllegalArgumentException, IOException {

    UserRequestValidator.validate(userRequest);

    User user = loginInteractor.getByUsername(userRequest.getUsername());

    PasswordHasher.verify(userRequest.getPassword(), user.getPassword());

    UserResponse userResponse = new UserResponse(user.getUid(), user.getUsername());
    String jws = ShieldJws.getJws();

    return new LoginResponse(userResponse, jws);
  }
}
