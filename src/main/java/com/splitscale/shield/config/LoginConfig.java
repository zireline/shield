package com.splitscale.shield.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.shield.jwt.JwtProvider;
import com.splitscale.shield.jwt.get.GetJwt;
import com.splitscale.shield.login.Login;
import com.splitscale.shield.user.read.ReadUserInteractor;
import com.splitscale.shield.user.workflows.login.LoginWorkflow;

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
  LoginWorkflow getLoginWorkflow(ReadUserInteractor interactor) {
    return new LoginWorkflow(interactor);
  }

}
