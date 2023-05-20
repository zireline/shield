package com.splitscale.shield.user.workflows.register;

import com.splitscale.shield.password.PasswordHasher;
import com.splitscale.shield.user.User;
import com.splitscale.shield.user.UserRequest;
import com.splitscale.shield.user.add.AddUserInteractor;
import java.util.UUID;

public class RegisterWorkflow {

  private AddUserInteractor interactor;

  public RegisterWorkflow(AddUserInteractor interactor) {
    this.interactor = interactor;
  }

  public void register(UserRequest request) {
    UUID randomUUID = UUID.randomUUID();
    String uid = randomUUID.toString();

    String encryptedPassword = PasswordHasher.encrypt(request.getPassword());

    User user = new User(uid, request.getUsername(), encryptedPassword);
    interactor.add(user);
  }
}
