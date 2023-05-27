package com.splitscale.shield.jwt;

import com.splitscale.shield.io.EnvFileHandler;
import com.splitscale.shield.io.PathProvider;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.io.IOException;
import java.nio.file.Path;

import javax.crypto.SecretKey;

public class SigningKeyWorkflow {

  private SigningKeyWorkflow() {
    // default
  }

  public static SecretKey getSigningKey() throws IOException {
    final String SIGNING_KEY_VARIABLE = "SIGNING_KEY";
    Path envFilePath = PathProvider.getEnvFilePath();

    EnvFileHandler envFileHandler = new EnvFileHandler(
        envFilePath,
        SIGNING_KEY_VARIABLE);

    envFileHandler.createEnvFileIfNotExist();

    String secretString = envFileHandler.getSigningKeyFromEnvFile();

    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
  }
}
