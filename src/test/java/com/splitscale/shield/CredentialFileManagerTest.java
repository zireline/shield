package com.splitscale.shield;

import com.google.gson.Gson;
import com.splitscale.shield.credential.Credential;
import com.splitscale.shield.io.CredentialFileManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CredentialFileManagerTest {
  private static final Path TEST_DIR_PATH = Path.of("src", "test", "resources", "credentials");
  private CredentialFileManager jsonFileManager;

  @BeforeEach
  public void setUp() {
    Gson gson = new Gson();
    jsonFileManager = new CredentialFileManager(gson, TEST_DIR_PATH);
  }

  @AfterEach
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
  public void testCRUDOperations() throws IOException {
    // Create a new user
    Credential newUser = new Credential("1", "userId", "jerome", "password");
    System.out.println("Creating User: " + newUser.getUsername());
    jsonFileManager.create(newUser);

    // Read the user object from the file
    Credential retrievedUser = jsonFileManager.read(newUser.getId());
    System.out.println("Retrieved User: " + retrievedUser.getUsername());
    assertEquals(newUser.getUsername(), retrievedUser.getUsername());

    // Update the user object
    retrievedUser.setPassword("newpassword");
    System.out.println("Updating User: " + retrievedUser.getUsername());
    jsonFileManager.update(retrievedUser);

    // Read the updated user object from the file
    Credential updatedUser = jsonFileManager.read(retrievedUser.getId());
    System.out.println("Updated User: " + updatedUser.getUsername());
    assertEquals(retrievedUser.getUsername(), updatedUser.getUsername());

    // Delete the user
    System.out.println("Deleting User: " + updatedUser.getUsername());
    jsonFileManager.delete(updatedUser.getId());

    // Verify the user is deleted
    Credential deletedUser = jsonFileManager.read(updatedUser.getId());
    assertNull(deletedUser);
  }

  @Test
  public void testReadAll() throws IOException {
    // Create multiple users
    Credential user1 = new Credential("1", "userId1", "john", "password1");
    Credential user2 = new Credential("2", "userId2", "jane", "password2");
    Credential user3 = new Credential("3", "userId3", "jacob", "password3");

    // Create users
    jsonFileManager.create(user1);
    jsonFileManager.create(user2);
    jsonFileManager.create(user3);

    // Read all users
    List<Credential> users = jsonFileManager.getAll();
    assertEquals(3, users.size());
  }
}
