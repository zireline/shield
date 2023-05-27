package com.splitscale.shield.io;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.UUID;

public class EnvFileHandler {

  private final Path envFilePath;
  private final String signingKeyVariable;

  public EnvFileHandler(Path envFilePath, String signingKeyVariable) {
    this.envFilePath = envFilePath.resolve(".env");
    this.signingKeyVariable = signingKeyVariable;
  }

  public void createEnvFileIfNotExist() throws IOException {
    if (!Files.exists(envFilePath)) {
      // Generate a signing key
      String signingKey = generateSigningKey();

      // Create .env file and write the signing key
      createEnvFile(signingKey);
    }
  }

  private void createEnvFile(String signingKey) throws IOException {
    // Create the directory if it doesn't exist
    Path directory = envFilePath.getParent();
    if (!Files.exists(directory)) {
      Files.createDirectories(directory);
    }

    try (BufferedWriter writer = Files.newBufferedWriter(envFilePath)) {
      writer.write(signingKeyVariable + "=" + signingKey);
    } catch (IOException e) {
      throw new IOException("Failed to create .env file.", e);
    }
  }

  private String generateSigningKey() {
    UUID signingKeyUUID = UUID.randomUUID();
    String signingKey = signingKeyUUID.toString();
    byte[] signingKeyBytes = signingKey.getBytes(StandardCharsets.UTF_8);
    return Base64.getEncoder().encodeToString(signingKeyBytes);
  }

  public String getSigningKeyFromEnvFile() throws IOException {
    Dotenv dotenv;
    try {
      dotenv = Dotenv.configure().directory(envFilePath.getParent().toString()).load();
    } catch (Exception e) {
      throw new IOException("Signing key retrieval error: " + e.getMessage());
    }

    String signingKey = dotenv.get(signingKeyVariable);

    if (signingKey != null && !signingKey.isEmpty()) {
      return signingKey;
    }

    throw new IOException("Signing key is empty");
  }
}
