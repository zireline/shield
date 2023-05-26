package com.splitscale.shield.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.shield.credential.read.ReadCredentialInteractor;
import com.splitscale.shield.endpoints.login.Login;
import com.splitscale.shield.jwt.JwtProvider;
import com.splitscale.shield.jwt.get.GetJwt;
import com.splitscale.shield.userinfo.read.ReadUserInfoInteractor;
import com.splitscale.shield.workflows.login.LoginWorkflow;

@Configuration
public class LoginConfig {
  @Bean
  Login getLogin(LoginWorkflow workflow, GetJwt provider) {
    return new Login(workflow, provider);
  }

  @Bean
  GetJwt getJwt(JwtProvider provider) {
    return new GetJwt(provider);
  }

  @Bean
  JwtProvider gJwtProvider() {
    return new JwtProvider();
  }

  @Bean
  LoginWorkflow getLoginWorkflow(ReadCredentialInteractor credentialInteractor,
      ReadUserInfoInteractor userInfoInteractor) {
    return new LoginWorkflow(credentialInteractor, userInfoInteractor);
  }

}
