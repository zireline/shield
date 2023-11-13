package com.splitscale.shield.io;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Storage<T> {
  private final Gson gson;
  private final Path directoryPath;
  final Class<T> typeParameterClass;

  public Storage(Gson gson, Path directoryPath, Class<T> typeParameterClass) {
    this.gson = gson;
    this.directoryPath = directoryPath;
    this.typeParameterClass = typeParameterClass;
  }

  public void create(String id, T object) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getFilePath(id);
    String json = gson.toJson(object);
    Files.write(filePath, json.getBytes());
  }

  public void create(T object) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    String id = UUID.randomUUID().toString();

    Path filePath = getFilePath(id);
    String json = gson.toJson(object);
    Files.write(filePath, json.getBytes());
  }

  public void update(String id, T object) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getFilePath(id);
    if (Files.exists(filePath)) {
      String json = gson.toJson(object);
      Files.write(filePath, json.getBytes());
    }
  }

  public void delete(String id) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getFilePath(id);
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

  public T getById(String id) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    Path filePath = getFilePath(id);
    if (Files.exists(filePath)) {
      String json = Files.readString(filePath);
      return gson.fromJson(json, typeParameterClass);
    }

    return null;
  }

  public List<T> getAll() throws IOException {
    createDirectoryIfNotExists(directoryPath);

    List<T> objects = new ArrayList<>();
    try (var stream = Files.newDirectoryStream(directoryPath, "*.json")) {
      for (Path filePath : stream) {
        String json = Files.readString(filePath);
        T object = gson.fromJson(json, typeParameterClass);
        objects.add(object);
      }
    }
    return objects;
  }

  public T getByKeyValue(String key, String value) throws IOException {
    createDirectoryIfNotExists(directoryPath);

    try (var stream = Files.newDirectoryStream(directoryPath, "*.json")) {
      for (Path filePath : stream) {
        String json = Files.readString(filePath);
        T object = gson.fromJson(json, typeParameterClass);

        // Assuming that the objects are represented as JSON objects
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonElement jsonElement = jsonObject.get(key);

        if (jsonElement != null && jsonElement.isJsonPrimitive()) {
          String elementValue = jsonElement.getAsString();
          if (elementValue.equals(value)) {
            return object;
          }
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

  private Path getFilePath(String id) {
    return directoryPath.resolve(id + ".json");
  }
}
