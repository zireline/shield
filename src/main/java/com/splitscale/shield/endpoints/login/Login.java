package com.splitscale.shield.endpoints.login;

import com.splitscale.shield.credential.CredentialRequest;
import com.splitscale.shield.jwt.get.GetJwt;
import com.splitscale.shield.repositories.ObjectNotFoundException;
import com.splitscale.shield.shielduser.ShieldUser;
import com.splitscale.shield.workflows.login.LoginWorkflow;

import io.jsonwebtoken.security.InvalidKeyException;
import java.io.IOException;

public class Login {

  LoginWorkflow workflow;
  GetJwt provider;

  public Login(LoginWorkflow workflow, GetJwt provider) {
    this.workflow = workflow;
    this.provider = provider;
  }

  public LoginResponse loginUser(CredentialRequest request)
      throws InvalidKeyException, IOException, ObjectNotFoundException {

    ShieldUser user = workflow.login(request);
    String token = provider.get(user.getId());
    return new LoginResponse(token, user);
  }
}
