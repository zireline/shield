package com.splitscale.shield.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

@Configuration
public class IoConfig {
  @Bean
  Gson getGson() {
    return new Gson();
  }

}
