package com.splitscale.shield.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonFileManager<T> {
    private final Gson gson;
    private final Path filePath;
  
    public JsonFileManager(Gson gson, Path filePath) {
      this.gson = gson;
      this.filePath = filePath;
    }

    public void create(T object) throws IOException {
        String json = gson.toJson(object);
        Files.write(filePath, json.getBytes());
    }

    public T read(Class<T> objectType) throws IOException {
        String json = Files.readString(filePath);
        try {
            return gson.fromJson(json, objectType);
        } catch (JsonSyntaxException e) {
            throw new IOException("Error parsing JSON data", e);
        }
    }

    public void update(T object) throws IOException {
        create(object);
    }

    public void delete() throws IOException {
        Files.deleteIfExists(filePath);
    }
}
