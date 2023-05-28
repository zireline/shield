package com.splitscale.shield.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.splitscale.shield.Shield;
import com.splitscale.shield.credential.CredentialRequest;
import com.splitscale.shield.endpoints.login.LoginResponse;
import com.splitscale.shield.endpoints.validate.ValidJwtResponse;
import com.splitscale.shield.io.PathProvider;
import com.splitscale.shield.repositories.ObjectNotFoundException;
import com.splitscale.shield.shielduser.ShieldUser;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class ShieldTest {
  ShieldUser shieldUser;

  @Autowired
  private Shield shield;

  @AfterAll
  public void tearDown() throws IOException {
    Path CRED_TEST_DIR_PATH = PathProvider.getCredentialsDir();
    Path USER_TEST_DIR_PATH = PathProvider.getUserInfosDir();
    Path ENV_TEST_DIR_PATH = PathProvider.getEnvFilePath();

    // Delete the test directory and its contents
    Files.walk(CRED_TEST_DIR_PATH)
        .map(Path::toFile)
        .forEach(file -> {
          if (!file.isDirectory() && file.getName().endsWith(".json")) {
            if (!file.delete()) {
              System.err.println("Failed to delete file: " + file);
            }
          }
        });
    Files.deleteIfExists(CRED_TEST_DIR_PATH);

    // Delete the test directory and its contents
    Files.walk(USER_TEST_DIR_PATH)
        .map(Path::toFile)
        .forEach(file -> {
          if (!file.isDirectory() && file.getName().endsWith(".json")) {
            if (!file.delete()) {
              System.err.println("Failed to delete file: " + file);
            }
          }
        });
    Files.deleteIfExists(USER_TEST_DIR_PATH);

    // Delete the test directory and its contents
    Files.walk(ENV_TEST_DIR_PATH)
        .map(Path::toFile)
        .forEach(file -> {
          if (!file.isDirectory() && file.getName().endsWith(".env")) {
            if (!file.delete()) {
              System.err.println("Failed to delete file: " + file);
            }
          }
        });
    Files.deleteIfExists(ENV_TEST_DIR_PATH);
  }

  @Test
  @Order(1)
  public void testRegisterUser() throws IOException {
    // Prepare test data
    CredentialRequest request = new CredentialRequest("joejoe", "password");

    // Verify that no exceptions are thrown
    String id = "";

    try {
      id = shield.registerUser(request);
      System.out.println("id: " + id);
    } catch (IllegalArgumentException e) {
      System.out.println("err: " + e.getMessage());
    }

    assertNotNull(id, "");
  }

  @Test
  @Order(2)
  public void testLoginUser() throws InvalidKeyException, IOException, ObjectNotFoundException {
    // Prepare test data
    CredentialRequest request = new CredentialRequest("joejoe", "password");

    // Verify that no exceptions are thrown
    assertDoesNotThrow(() -> {
      shieldUser = shield.loginUser(request).getUser();
    });
  }

  @Test
  @Order(3)
  public void testValidateJwt() throws IOException, ObjectNotFoundException, GeneralSecurityException {
    // Prepare test data
    CredentialRequest request = new CredentialRequest("joejoe", "password");

    LoginResponse response = shield.loginUser(request);

    // Verify that no exceptions are thrown
    ValidJwtResponse validJwt = shield.validateJwt(response.getToken(), response.getUser().getId());
    assertNotNull(validJwt);

    System.out.println(validJwt.getToken());

    System.out.println(validJwt.getClaims().getId());
    System.out.println(validJwt.getClaims().getAudience());
    System.out.println(validJwt.getClaims().getIssuer());
  }

  @Test
  @Order(4)
  public void testInValidateJwt()
      throws InvalidKeyException, IOException, ObjectNotFoundException, GeneralSecurityException {
    // Prepare test data
    CredentialRequest request = new CredentialRequest("joejoe", "password");

    LoginResponse response = shield.loginUser(request);

    String invalidJwt = shield.inValidateJwt(response.getToken());
    assertNotNull(invalidJwt);

    System.out.println("Invalid Jwt: " + invalidJwt);
  }

  @Test
  @Order(5)
  public void updateShieldUser() throws IOException {
    // Prepare test data
    shieldUser.setFirstName("John"); // Set the updated first name
    shieldUser.setLastName("Doe"); // Set the updated last name

    // Verify that no exceptions are thrown
    assertDoesNotThrow(() -> shield.updateShieldUser(shieldUser));

    CredentialRequest request = new CredentialRequest("joejoe", "password");

    assertDoesNotThrow(() -> System.out.println(shield.loginUser(request).getUser().getFirstName()));
  }
}