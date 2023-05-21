package com.splitscale.shield.io;

import java.nio.file.Path;

public class PathProvider {
  private PathProvider() {
    // default
  }

  public static String getEnvFilePath() {
    return Path.of("src", "main", "resources", "jwt.env").toString();
  }

  public static Path getUsersDir() {
    return Path.of("src", "main", "resources", "users");
  }
}
