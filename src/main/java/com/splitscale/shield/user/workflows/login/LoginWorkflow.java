package com.splitscale.shield.user.workflows.login;

import java.io.IOException;

import com.splitscale.shield.password.PasswordHasher;
import com.splitscale.shield.user.User;
import com.splitscale.shield.user.UserRequest;
import com.splitscale.shield.user.UserResponse;
import com.splitscale.shield.user.read.ReadUserInteractor;
import com.splitscale.shield.user.repository.ObjectNotFoundException;

public class LoginWorkflow {

  private ReadUserInteractor interactor;

  public LoginWorkflow(ReadUserInteractor interactor) {
    this.interactor = interactor;
  }

  public UserResponse login(UserRequest request) throws IOException, ObjectNotFoundException {
    User user = interactor.getByUsername(request.getUsername());

    if (user == null) {
      throw new ObjectNotFoundException("User " + request.getUsername() + " not found");
    }

    PasswordHasher.verify(request.getPassword(), user.getPassword());

    return new UserResponse(user.getId(), user.getUsername());
  }
}
