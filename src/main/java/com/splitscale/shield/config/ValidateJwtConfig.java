package com.splitscale.shield.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.splitscale.shield.endpoints.validate.ValidateJwt;
import com.splitscale.shield.jwt.JwtProvider;

@Configuration
public class ValidateJwtConfig {
  @Bean
  ValidateJwt getValidateJwt(JwtProvider provider) {
    return new ValidateJwt(provider);
  }
}
