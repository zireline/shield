package com.splitscale.shield.config;

import java.nio.file.Path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.splitscale.shield.io.JsonFileManager;
import com.splitscale.shield.io.PathProvider;
import com.splitscale.shield.user.read.ReadUserInteractor;
import com.splitscale.shield.user.repository.UserRepository;
import com.splitscale.shield.user.repository.UserRepositoryImpl;

@Configuration
public class UserRepositoryConfig {
  @Bean
  ReadUserInteractor getReadUserInteractor(UserRepository repository) {
    return new ReadUserInteractor(repository);
  }

  @Bean
  UserRepository getUserRepository(JsonFileManager jsonFileManager) {
    return new UserRepositoryImpl(jsonFileManager);
  }

  @Bean
  JsonFileManager getFileManager(Gson gson, Path directoryPath) {
    return new JsonFileManager(gson, directoryPath);
  }

  @Bean
  Gson getGson() {
    return new Gson();
  }

  @Bean
  Path getPath() {
    return PathProvider.getUsersDir();
  }
}
