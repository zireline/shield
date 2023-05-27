package com.splitscale.shield;

import com.google.gson.Gson;
import com.splitscale.shield.credential.Credential;
import com.splitscale.shield.io.CredentialFileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class CredentialFileManagerTest {
  private static final Path TEST_DIR_PATH = Path.of("src", "test", "resources", "credentials");
  private static CredentialFileManager jsonFileManager;
  private String id;

  @BeforeAll
  public static void setUp() {
    Gson gson = new Gson();
    jsonFileManager = new CredentialFileManager(gson, TEST_DIR_PATH);
  }

  @AfterAll
  public void tearDown() throws IOException {
    // Delete the test directory and its contents
    Files.walk(TEST_DIR_PATH)
        .map(Path::toFile)
        .forEach(file -> {
          if (!file.isDirectory() && file.getName().endsWith(".json")) {
            if (!file.delete()) {
              System.err.println("Failed to delete file: " + file);
            }
          }
        });
    Files.deleteIfExists(TEST_DIR_PATH);
  }

  @Test
  @Order(1)
  public void testCreateCredential() throws IOException {
    Credential newUser = new Credential(UUID.randomUUID().toString(), "userId", "joejoe", "password");

    id = jsonFileManager.create(newUser);

    assertNotNull(id);
  }

  @Test
  @Order(2)
  public void testUpdateCredential() throws IOException {
    String UPDATED_PWD = "newpassword";

    Credential retrievedCredential = jsonFileManager.read(id);

    retrievedCredential.setPassword(UPDATED_PWD);

    jsonFileManager.update(retrievedCredential);

    Credential updatedCredential = jsonFileManager.read(retrievedCredential.getId());

    assertEquals(retrievedCredential.getUsername(), updatedCredential.getUsername());
  }

  @Test
  @Order(3)
  public void testDeleteCredential() throws IOException {
    jsonFileManager.delete(id);

    Credential deletedCredential = jsonFileManager.read(id);

    assertNull(deletedCredential);
  }

  @Test
  @Order(5)
  public void testReadAllCredentials() throws IOException {
    Credential credential1 = new Credential(UUID.randomUUID().toString(), "userId1", "john", "password1");
    Credential credential2 = new Credential(UUID.randomUUID().toString(), "userId2", "jane", "password2");
    Credential credential3 = new Credential(UUID.randomUUID().toString(), "userId3", "jacob", "password3");

    jsonFileManager.create(credential1);
    jsonFileManager.create(credential2);
    jsonFileManager.create(credential3);

    List<Credential> users = jsonFileManager.getAll();
    assertEquals(3, users.size());
  }
}
