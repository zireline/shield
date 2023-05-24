package com.splitscale.shield.user.workflows.register;

import com.splitscale.shield.password.PasswordHasher;
import com.splitscale.shield.user.User;
import com.splitscale.shield.user.UserRequest;
import com.splitscale.shield.user.add.AddUserInteractor;
import com.splitscale.shield.user.read.ReadUserInteractor;

import java.io.IOException;
import java.util.UUID;

public class RegisterWorkflow {

  private AddUserInteractor interactor;
  private ReadUserInteractor userReader;

  public RegisterWorkflow(AddUserInteractor interactor, ReadUserInteractor userReader) {
    this.interactor = interactor;
    this.userReader = userReader;
  }

  public String register(UserRequest request) throws IOException, IllegalArgumentException {
    User existingUser = userReader.getByUsername(request.getUsername());

    if (existingUser.getId() != null) {
      throw new IllegalArgumentException("user " + request.getUsername() + "already exists");
    }

    UUID randomUUID = UUID.randomUUID();
    String uid = randomUUID.toString();

    String encryptedPassword = PasswordHasher.encrypt(request.getPassword());

    User user = new User(uid, request.getUsername(), encryptedPassword);
    return interactor.add(user);
  }
}
