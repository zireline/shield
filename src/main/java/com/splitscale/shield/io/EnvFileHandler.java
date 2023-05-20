package com.splitscale.shield.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public class EnvFileHandler {

  private final String envFilePath;
  private final String signingKeyVariable;

  public EnvFileHandler(String envFilePath, String signingKeyVariable) {
    this.envFilePath = envFilePath;
    this.signingKeyVariable = signingKeyVariable;
  }

  public void handleEnvFile() throws IOException {
    File envFile = new File(envFilePath);

    if (!envFile.exists()) {
      // Generate a signing key
      String signingKey = generateSigningKey();

      // Create .env file and write the signing key
      createEnvFile(signingKey);
    }
  }

  private void createEnvFile(String signingKey) throws IOException {
    File envFile = new File(envFilePath);

    // Create the directory if it doesn't exist
    File directory = envFile.getParentFile();
    if (!directory.exists() && !directory.mkdirs()) {
      throw new IOException("Failed to create directory for .env file.");
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(envFile))) {
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
    try (
      BufferedReader reader = new BufferedReader(new FileReader(envFilePath))
    ) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.startsWith(signingKeyVariable)) {
          String[] parts = line.split("=");
          if (parts.length == 2) {
            return parts[1].trim();
          }
        }
      }
      throw new IOException("Signing key not found in .env file.");
    } catch (IOException e) {
      throw new IOException("Failed to read .env file.", e);
    }
  }
}
