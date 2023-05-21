package com.splitscale.shield.config;

import java.nio.file.Path;

import com.google.gson.Gson;
import com.splitscale.shield.io.JsonFileManager;
import com.splitscale.shield.io.PathProvider;
import com.splitscale.shield.user.add.AddUserInteractor;
import com.splitscale.shield.user.read.ReadUserInteractor;
import com.splitscale.shield.user.repository.UserRepository;
import com.splitscale.shield.user.repository.UserRepositoryImpl;

public class UserRepositoryConfig {

  private UserRepositoryConfig() {
    // default configuration
  }

  public static AddUserInteractor getAddUserInteractor() {
    UserRepository repository = getUserRepository();
    return new AddUserInteractor(repository);
  }

  public static ReadUserInteractor getReadUserInteractor() {
    UserRepository repository = getUserRepository();
    return new ReadUserInteractor(repository);
  }

  public static UserRepository getUserRepository() {
    JsonFileManager jsonFileManager = getFileManager(getGson(), getPath());
    return new UserRepositoryImpl(jsonFileManager);
  }

  public static JsonFileManager getFileManager(Gson gson, Path directoryPath) {
    return new JsonFileManager(gson, directoryPath);
  }

  public static Gson getGson() {
    return new Gson();
  }

  public static Path getPath() {
    return PathProvider.getUsersDir();
  }
}
