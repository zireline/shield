package com.splitscale.shield.jwt;

import com.splitscale.shield.io.EnvFileHandler;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.io.IOException;

import javax.crypto.SecretKey;

public class SigningKeyWorkflow {

  private SigningKeyWorkflow() {
    // default
  }

  public static SecretKey getSigningKey() throws IOException {
    final String SIGNING_KEY_VARIABLE = "SIGNING_KEY";
    String envFilePath = getEnvFilePath();

    EnvFileHandler envFileHandler = new EnvFileHandler(
      envFilePath,
      SIGNING_KEY_VARIABLE
    );

    // Call the handleEnvFile method to ensure the env file exists
    envFileHandler.handleEnvFile();

    // Get the signing key from the env file
    String secretString = envFileHandler.getSigningKeyFromEnvFile();

    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
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
