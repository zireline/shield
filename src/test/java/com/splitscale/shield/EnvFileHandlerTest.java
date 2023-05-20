package com.splitscale.shield;

import com.splitscale.shield.io.EnvFileHandler;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.*;

public class EnvFileHandlerTest {

  private static final String SIGNING_KEY_VARIABLE = "SIGNING_KEY";
  private static final String ENV_FILE_PATH = getEnvFilePath();

  @AfterEach
  public void cleanup() {
    File envFile = new File(ENV_FILE_PATH);
    if (envFile.exists()) {
      envFile.delete();
    }
  }

  @Test
  public void testHandleEnvFile() throws IOException {
    EnvFileHandler envFileHandler = new EnvFileHandler(
      ENV_FILE_PATH,
      SIGNING_KEY_VARIABLE
    );

    // Ensure the env file doesn't exist initially
    Assertions.assertFalse(new File(ENV_FILE_PATH).exists());

    // Call the handleEnvFile method
    envFileHandler.handleEnvFile();

    // If the env file was created, print the signing key
    if (new File(ENV_FILE_PATH).exists()) {
      String signingKey = envFileHandler.getSigningKeyFromEnvFile();
      System.out.println("Signing Key: " + signingKey);
    }
  }

  @Test
  public void testGetSigningKeyFromEnvFile() throws IOException {
    File tempEnvFile = new File(ENV_FILE_PATH);

    // Write the signing key to the temp env file
    String signingKey = "abc123";
    try (
      BufferedWriter writer = new BufferedWriter(new FileWriter(tempEnvFile))
    ) {
      writer.write(SIGNING_KEY_VARIABLE + "=" + signingKey);
    }

    EnvFileHandler envFileHandler = new EnvFileHandler(
      ENV_FILE_PATH,
      SIGNING_KEY_VARIABLE
    );

    // Call the getSigningKeyFromEnvFile method
    String retrievedSigningKey = envFileHandler.getSigningKeyFromEnvFile();

    // Assert that the retrieved signing key matches the expected value
    Assertions.assertEquals(signingKey, retrievedSigningKey);
  }

  private static String getEnvFilePath() {
    String currentDir = System.getProperty("user.dir");
    String fileSeparator = System.getProperty("file.separator");
    return (
      currentDir +
      fileSeparator +
      "src" +
      fileSeparator +
      "main" +
      fileSeparator +
      "resources" +
      fileSeparator +
      "test.env"
    );
  }
}
