package com.splitscale.shield.io;

import com.google.gson.Gson;
import com.splitscale.shield.user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonFileManager {
  private final Gson gson;
  private final Path directoryPath;

  public JsonFileManager(Gson gson, Path directoryPath) {
    this.gson = gson;
    this.directoryPath = directoryPath;
  }

  public void create(User user) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getUserFilePath(user.getId());
    String json = gson.toJson(user);
    Files.write(filePath, json.getBytes());
  }

  public User read(String userId) throws IOException {
    Path filePath = getUserFilePath(userId);
    if (Files.exists(filePath)) {
      String json = Files.readString(filePath);
      return gson.fromJson(json, User.class);
    }
    return null;
  }

  public void update(User user) throws IOException {
    Path filePath = getUserFilePath(user.getId());
    if (Files.exists(filePath)) {
      String json = gson.toJson(user);
      Files.write(filePath, json.getBytes());
    }
  }

  public void delete(User user) throws IOException {
    Path filePath = getUserFilePath(user.getId());
    if (Files.exists(filePath)) {
      Files.delete(filePath);
    }
  }

  public User getById(String id) throws IOException {
    Path filePath = getUserFilePath(id);
    if (Files.exists(filePath)) {
      String json = Files.readString(filePath);
      return gson.fromJson(json, User.class);
    }
    return null;
  }

  public List<User> getAll() throws IOException {
    List<User> users = new ArrayList<>();
    try (var stream = Files.newDirectoryStream(directoryPath, "*.json")) {
      for (Path filePath : stream) {
        String json = Files.readString(filePath);
        User user = gson.fromJson(json, User.class);
        users.add(user);
      }
    }
    return users;
  }

  public User getByUsername(String username) throws IOException {
    try (var stream = Files.newDirectoryStream(directoryPath, "*.json")) {
      for (Path filePath : stream) {
        String json = Files.readString(filePath);
        User user = gson.fromJson(json, User.class);
        if (username.equals(user.getUsername())) {
          return user;
        }
      }
    }
    return null;
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
