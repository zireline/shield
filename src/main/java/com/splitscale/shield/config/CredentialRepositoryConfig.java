package com.splitscale.shield.config;

import java.nio.file.Path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.splitscale.shield.credential.read.ReadAllCredentialInteractor;
import com.splitscale.shield.credential.read.ReadCredentialInteractor;
import com.splitscale.shield.io.CredentialFileManager;
import com.splitscale.shield.io.PathProvider;
import com.splitscale.shield.repositories.CredentialRepository;
import com.splitscale.shield.repositories.impl.CredentialRepositoryImpl;

@Configuration
public class CredentialRepositoryConfig {
  @Bean
  ReadCredentialInteractor getReadUserInteractor(CredentialRepository repository) {
    return new ReadCredentialInteractor(repository);
  }

  @Bean
  ReadAllCredentialInteractor getReadAllUserInteractor(CredentialRepository repository) {
    return new ReadAllCredentialInteractor(repository);
  }

  @Bean
  CredentialRepository getUserRepository(CredentialFileManager jsonFileManager) {
    return new CredentialRepositoryImpl(jsonFileManager);
  }

  @Bean
  CredentialFileManager getCredentialFileManager(Gson gson) {
    Path directoryPath = PathProvider.getCredentialsDir();
    return new CredentialFileManager(gson, directoryPath);
  }

}
