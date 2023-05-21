package com.splitscale.shield.config;

import com.splitscale.shield.jwt.JwtProvider;
import com.splitscale.shield.jwt.get.GetJwt;
import com.splitscale.shield.login.Login;
import com.splitscale.shield.user.read.ReadUserInteractor;
import com.splitscale.shield.user.workflows.login.LoginWorkflow;

public class LoginConfig {

  private LoginConfig() {
    // default
  }

  public static Login getLogin() {
    JwtProvider jwtProvider = JwtConfig.getJwtProvider();
    GetJwt getJwt = JwtConfig.getJwt(jwtProvider);

    ReadUserInteractor interactor = UserRepositoryConfig.getReadUserInteractor();
    LoginWorkflow workflow = getLoginWorkflow(interactor);

    return new Login(workflow, getJwt);
  }

  private static LoginWorkflow getLoginWorkflow(ReadUserInteractor interactor) {
    return new LoginWorkflow(interactor);
  }

}
