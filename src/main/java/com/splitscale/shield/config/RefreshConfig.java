package com.splitscale.shield.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.shield.endpoints.refresh.Refresh;
import com.splitscale.shield.jwt.get.GetJwt;
import com.splitscale.shield.refreshtoken.RefreshTokenManager;

@Configuration
public class RefreshConfig {
  @Bean
  Refresh getRefresh(RefreshTokenManager refreshTokenProvider, GetJwt accessTokenProvider) {
    return new Refresh(refreshTokenProvider, accessTokenProvider);
  }
}
