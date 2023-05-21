package com.splitscale.shield;

import com.google.gson.Gson;
import com.splitscale.shield.io.JsonFileManager;
import com.splitscale.shield.user.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonFileManagerTest {
  private static final Path TEST_DIR_PATH = Path.of(getUsersDir());
  private JsonFileManager jsonFileManager;

  @BeforeEach
  public void setUp() {
    Gson gson = new Gson();
    try {
      jsonFileManager = new JsonFileManager(gson, TEST_DIR_PATH);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @AfterEach
  public void tearDown() throws IOException {
    // Delete the test directory and its contents
    Files.walk(TEST_DIR_PATH)
        .map(Path::toFile)
        .forEach(file -> {
          if (!file.delete()) {
            System.err.println("Failed to delete file: " + file);
          }
        });
    Files.deleteIfExists(TEST_DIR_PATH);
  }

  @Test
  public void testCRUDOperations() throws IOException {
    // Create a new user
    User newUser = new User("1", "jerome", "password");
    System.out.println("Creating User: " + newUser.getUsername());
    jsonFileManager.create(newUser);

    // Read the user object from the file
    User retrievedUser = jsonFileManager.read(newUser.getId());
    System.out.println("Retrieved User: " + retrievedUser.getUsername());
    assertEquals(newUser.getUsername(), retrievedUser.getUsername());

    // Update the user object
    retrievedUser.setPassword("newpassword");
    System.out.println("Updating User: " + retrievedUser.getUsername());
    jsonFileManager.update(retrievedUser);

    // Read the updated user object from the file
    User updatedUser = jsonFileManager.read(retrievedUser.getId());
    System.out.println("Updated User: " + updatedUser.getUsername());
    assertEquals(retrievedUser.getUsername(), updatedUser.getUsername());

    // Delete the user
    System.out.println("Deleting User: " + updatedUser.getUsername());
    jsonFileManager.delete(updatedUser);

    // Verify the user is deleted
    User deletedUser = jsonFileManager.read(updatedUser.getId());
    assertNull(deletedUser);
  }

  @Test
  public void testReadAll() throws IOException {
    // Create multiple users
    User user1 = new User("1", "john", "password1");
    User user2 = new User("2", "jane", "password2");
    User user3 = new User("3", "jacob", "password3");

    // Create users
    jsonFileManager.create(user1);
    jsonFileManager.create(user2);
    jsonFileManager.create(user3);

    // Read all users
    List<User> users = jsonFileManager.getAll();
    assertEquals(3, users.size());
  }

  private static String getUsersDir() {
    String currentDir = System.getProperty("user.dir");
    String fileSeparator = System.getProperty("file.separator");
    return (currentDir +
        fileSeparator +
        "src" +
        fileSeparator +
        "test" +
        fileSeparator +
        "resources" +
        fileSeparator +
        "users");
  }
}
