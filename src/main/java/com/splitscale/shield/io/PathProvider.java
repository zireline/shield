package com.splitscale.shield.io;

import java.nio.file.Path;

public class PathProvider {
  private PathProvider() {
    // default
  }

  public static String getEnvFilePath() {
    return Path.of("src", "main", "resources", "jwt.env").toString();
  }

  public static Path getUserInfosDir() {
    return Path.of("src", "main", "resources", "userInfos");
  }

  public static Path getCredentialsDir() {
    return Path.of("src", "main", "resources", "credentials");
  }
}
