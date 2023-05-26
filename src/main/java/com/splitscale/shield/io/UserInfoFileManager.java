package com.splitscale.shield.io;

import com.google.gson.Gson;
import com.splitscale.shield.userinfo.UserInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UserInfoFileManager {
  private final Gson gson;
  private final Path directoryPath;

  public UserInfoFileManager(Gson gson, Path directoryPath) {
    this.gson = gson;
    this.directoryPath = directoryPath;
  }

  public void create(UserInfo user) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getUserFilePath(user.getId());
    String json = gson.toJson(user);
    Files.write(filePath, json.getBytes());
  }

  public UserInfo read(String userId) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getUserFilePath(userId);
    if (Files.exists(filePath)) {
      String json = Files.readString(filePath);
      return gson.fromJson(json, UserInfo.class);
    }
    return null;
  }

  public void update(UserInfo user) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getUserFilePath(user.getId());
    if (Files.exists(filePath)) {
      String json = gson.toJson(user);
      Files.write(filePath, json.getBytes());
    }
  }

  public void delete(String id) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getUserFilePath(id);
    if (Files.exists(filePath)) {
      Files.delete(filePath);
    }
  }

  public UserInfo getById(String id) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getUserFilePath(id);
    if (Files.exists(filePath)) {
      String json = Files.readString(filePath);
      return gson.fromJson(json, UserInfo.class);
    }
    return null;
  }

  public List<UserInfo> getAll() throws IOException {
    createDirectoryIfNotExists(directoryPath);

    List<UserInfo> users = new ArrayList<>();
    try (var stream = Files.newDirectoryStream(directoryPath, "*.json")) {
      for (Path filePath : stream) {
        String json = Files.readString(filePath);
        UserInfo user = gson.fromJson(json, UserInfo.class);
        users.add(user);
      }
    }
    return users;
  }

  public void deleteAll() throws IOException {
    createDirectoryIfNotExists(directoryPath);

    try (var stream = Files.newDirectoryStream(directoryPath, "*.json")) {
      for (Path filePath : stream) {
        Files.delete(filePath);
      }
    }
  }

  private void createDirectoryIfNotExists(Path directoryPath) throws IOException {
    if (!Files.exists(directoryPath)) {
      Files.createDirectories(directoryPath);
    }
  }

  private Path getUserFilePath(String userId) {
    return directoryPath.resolve(userId + ".json");
  }
}
