package com.splitscale.shield;

import com.google.gson.Gson;
import com.splitscale.shield.io.JsonFileManager;
import com.splitscale.shield.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class JsonFileManagerTest {
  private static final Path TEST_FILE_PATH = Path.of("test.json");
  private JsonFileManager<User> jsonFileManager;

  @BeforeEach
  public void setUp() {
    Gson gson = new Gson();
    jsonFileManager = new JsonFileManager<>(gson, TEST_FILE_PATH);
  }

  @Test
  public void testCRUDOperations() throws IOException {
    // Create a new user
    User newUser = new User("1", "jerome", "password");
    System.out.println("Creating User: " + newUser.getUsername());
    jsonFileManager.create(newUser);

    // Read the user object from the file
    User retrievedUser = jsonFileManager.read(User.class);
    System.out.println("Retrieved User: " + retrievedUser.getUsername());
    assertEquals(newUser.getUsername(), retrievedUser.getUsername());

    // Update the user object
    retrievedUser.setPassword("newpassword");
    System.out.println("Updating User: " + retrievedUser.getUsername());
    jsonFileManager.update(retrievedUser);

    // Read the updated user object from the file
    User updatedUser = jsonFileManager.read(User.class);
    System.out.println("Updated User: " + updatedUser.getUsername());
    assertEquals(retrievedUser.getUsername(), updatedUser.getUsername());

    // Delete the JSON file
    System.out.println("Deleting User");
    jsonFileManager.delete();
    assertFalse(Files.exists(TEST_FILE_PATH));
  }
}
