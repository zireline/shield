package com.splitscale.shield.config;

import java.nio.file.Path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.splitscale.shield.io.PathProvider;
import com.splitscale.shield.io.UserInfoFileManager;
import com.splitscale.shield.repositories.UserInfoRepository;
import com.splitscale.shield.repositories.impl.UserInfoRepositoryImpl;
import com.splitscale.shield.userinfo.add.AddUserInfoInteractor;
import com.splitscale.shield.userinfo.read.ReadUserInfoInteractor;

@Configuration
public class UserInfoRepositoryConfig {
  @Bean
  ReadUserInfoInteractor getReadUserInfoInteractor(UserInfoRepository repository) {
    return new ReadUserInfoInteractor(repository);
  }

  @Bean
  AddUserInfoInteractor getAddUserInfoInteractor(UserInfoRepository repository) {
    return new AddUserInfoInteractor(repository);
  }

  @Bean
  UserInfoRepository getUserInfoRepository(UserInfoFileManager fileManager) {
    return new UserInfoRepositoryImpl(fileManager);
  }

  @Bean
  UserInfoFileManager getUserInfoFileManager(Gson gson) {
    Path directoryPath = PathProvider.getUserInfosDir();
    return new UserInfoFileManager(gson, directoryPath);
  }
}
