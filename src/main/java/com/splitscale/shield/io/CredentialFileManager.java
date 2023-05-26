package com.splitscale.shield.io;

import com.google.gson.Gson;
import com.splitscale.shield.credential.Credential;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CredentialFileManager {
  private final Gson gson;
  private final Path directoryPath;

  public CredentialFileManager(Gson gson, Path directoryPath) {
    this.gson = gson;
    this.directoryPath = directoryPath;
  }

  public String create(Credential credential) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getUserFilePath(credential.getId());
    String json = gson.toJson(credential);
    Files.write(filePath, json.getBytes());
    return credential.getId();
  }

  public Credential read(String userId) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getUserFilePath(userId);
    if (Files.exists(filePath)) {
      String json = Files.readString(filePath);
      return gson.fromJson(json, Credential.class);
    }
    return null;
  }

  public void update(Credential user) throws IOException {
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

  public void deleteAll() throws IOException {
    createDirectoryIfNotExists(directoryPath);

    try (var stream = Files.newDirectoryStream(directoryPath, "*.json")) {
      for (Path filePath : stream) {
        Files.delete(filePath);
      }
    }
  }

  public Credential getById(String id) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getUserFilePath(id);
    if (Files.exists(filePath)) {
      String json = Files.readString(filePath);
      return gson.fromJson(json, Credential.class);
    }
    return null;
  }

  public List<Credential> getAll() throws IOException {
    createDirectoryIfNotExists(directoryPath);

    List<Credential> users = new ArrayList<>();
    try (var stream = Files.newDirectoryStream(directoryPath, "*.json")) {
      for (Path filePath : stream) {
        String json = Files.readString(filePath);
        Credential user = gson.fromJson(json, Credential.class);
        users.add(user);
      }
    }
    return users;
  }

  public Credential getByUsername(String username) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    try (var stream = Files.newDirectoryStream(directoryPath, "*.json")) {
      for (Path filePath : stream) {
        String json = Files.readString(filePath);
        Credential user = gson.fromJson(json, Credential.class);
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
