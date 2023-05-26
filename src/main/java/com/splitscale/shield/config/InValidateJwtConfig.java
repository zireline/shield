package com.splitscale.shield.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.shield.endpoints.invalidate.InvalidateJwt;
import com.splitscale.shield.jwt.JwtProvider;

@Configuration
public class InValidateJwtConfig {
  @Bean
  InvalidateJwt getInValidateJwt(JwtProvider provider) {
    return new InvalidateJwt(provider);
  }
}
