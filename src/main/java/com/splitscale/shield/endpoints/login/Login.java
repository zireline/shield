package com.splitscale.shield.endpoints.login;

import com.splitscale.shield.credential.CredentialRequest;
import com.splitscale.shield.jwt.get.GetJwt;
import com.splitscale.shield.refreshtoken.RefreshTokenManager;
import com.splitscale.shield.repositories.ObjectNotFoundException;
import com.splitscale.shield.shielduser.ShieldUser;
import com.splitscale.shield.workflows.login.LoginWorkflow;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.security.InvalidKeyException;
import java.io.IOException;

public class Login {

  private LoginWorkflow workflow;
  private GetJwt accessTokenProvider;
  private RefreshTokenManager refreshTokenProvider;

  public Login(LoginWorkflow workflow, GetJwt accessTokenProvider, RefreshTokenManager refreshTokenProvider) {
    this.workflow = workflow;
    this.accessTokenProvider = accessTokenProvider;
    this.refreshTokenProvider = refreshTokenProvider;
  }

  public LoginResponse loginUser(CredentialRequest request)
      throws InvalidKeyException, IOException, ObjectNotFoundException {

    ShieldUser user = workflow.login(request);
    String uid = user.getId();

    String accessToken = accessTokenProvider.get(uid);
    String refreshToken = refreshTokenProvider.generateRefreshToken(uid, 30, TimeUnit.DAYS);

    Tokens tokens = new Tokens(accessToken, refreshToken);
    return new LoginResponse(tokens, user);
  }
}
