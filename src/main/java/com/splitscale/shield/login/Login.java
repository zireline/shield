package com.splitscale.shield.login;

import com.splitscale.shield.jwt.get.GetJwt;
import com.splitscale.shield.user.UserRequest;
import com.splitscale.shield.user.UserResponse;
import com.splitscale.shield.user.repository.ObjectNotFoundException;
import com.splitscale.shield.user.workflows.login.LoginWorkflow;
import io.jsonwebtoken.security.InvalidKeyException;
import java.io.IOException;

public class Login {

  LoginWorkflow workflow;
  GetJwt provider;

  public Login(LoginWorkflow workflow, GetJwt provider) {
    this.workflow = workflow;
    this.provider = provider;
  }

  public LoginResponse loginUser(UserRequest request)
      throws InvalidKeyException, IOException, ObjectNotFoundException {

    UserResponse userResponse = workflow.login(request);
    String token = provider.get(userResponse.getId());
    return new LoginResponse(token, userResponse);
  }
}
