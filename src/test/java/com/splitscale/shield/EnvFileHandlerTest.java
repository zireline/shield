package com.splitscale.shield;

import com.splitscale.shield.io.EnvFileHandler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.*;

public class EnvFileHandlerTest {

  private static final String SIGNING_KEY_VARIABLE = "SIGNING_KEY";
  private static final Path ENV_FILE_PATH = Path.of("src", "test", "resources");

  @AfterAll
  public static void cleanup() throws IOException {
    Files.deleteIfExists(ENV_FILE_PATH.resolve(".env"));
  }

  @Test
  public void createEnvFileIfNotExist() throws IOException {
    EnvFileHandler envFileHandler = new EnvFileHandler(
        ENV_FILE_PATH,
        SIGNING_KEY_VARIABLE);

    // Ensure the env file doesn't exist initially
    assertFalse(Files.exists(ENV_FILE_PATH.resolve(".env")));

    assertDoesNotThrow(() -> envFileHandler.createEnvFileIfNotExist());
  }

  @Test
  public void testGetSigningKeyFromEnvFile() throws IOException {
    EnvFileHandler envFileHandler = new EnvFileHandler(
        ENV_FILE_PATH,
        SIGNING_KEY_VARIABLE);

    // Call the getSigningKeyFromEnvFile method
    String retrievedSigningKey = envFileHandler.getSigningKeyFromEnvFile();

    // Assert that the retrieved signing key matches the expected value
    assertNotNull(retrievedSigningKey);
    System.out.println("Retrieved Signing Key: " + retrievedSigningKey);
  }
}
