package com.splitscale.shield.user.workflows.login;

import com.splitscale.shield.password.PasswordHasher;
import com.splitscale.shield.user.User;
import com.splitscale.shield.user.UserRequest;
import com.splitscale.shield.user.UserResponse;
import com.splitscale.shield.user.read.ReadUserInteractor;

public class LoginWorkflow {

  private ReadUserInteractor interactor;

  public LoginWorkflow(ReadUserInteractor interactor) {
    this.interactor = interactor;
  }

  public UserResponse login(UserRequest request) {
    User user = interactor.getByUsername(request.getUsername());
    
    PasswordHasher.verify(request.getPassword(), user.getPassword());
    
    return new UserResponse(user.getId(), user.getUsername());
  }
}
