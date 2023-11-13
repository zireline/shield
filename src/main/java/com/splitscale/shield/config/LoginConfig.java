package com.splitscale.shield.config;

import java.nio.file.Path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.splitscale.shield.credential.read.ReadCredentialInteractor;
import com.splitscale.shield.endpoints.login.Login;
import com.splitscale.shield.io.PathProvider;
import com.splitscale.shield.io.Storage;
import com.splitscale.shield.jwt.JwtProvider;
import com.splitscale.shield.jwt.get.GetJwt;
import com.splitscale.shield.refreshtoken.RefreshToken;
import com.splitscale.shield.refreshtoken.RefreshTokenManager;
import com.splitscale.shield.refreshtoken.TokenStorage;
import com.splitscale.shield.repositories.impl.TokenStorageImpl;
import com.splitscale.shield.userinfo.read.ReadUserInfoInteractor;
import com.splitscale.shield.workflows.login.LoginWorkflow;

@Configuration
public class LoginConfig {
  @Bean
  Login getLogin(LoginWorkflow workflow, GetJwt accessTokenProvider, RefreshTokenManager refreshTokenProvider) {
    return new Login(workflow, accessTokenProvider, refreshTokenProvider);
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

  @Bean
  RefreshTokenManager getRefreshTokenManager(TokenStorage storage) {
    return new RefreshTokenManager(storage);
  }

  @Bean
  TokenStorage getTokenStorage(Gson gson) {
    Path directoryPath = PathProvider.getRefreshTokensDir();

    Storage<RefreshToken> storage = new Storage<RefreshToken>(gson, directoryPath, RefreshToken.class);
    return new TokenStorageImpl(storage);
  }

}
