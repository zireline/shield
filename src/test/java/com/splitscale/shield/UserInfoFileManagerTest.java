package com.splitscale.shield;

import com.google.gson.Gson;
import com.splitscale.shield.io.UserInfoFileManager;
import com.splitscale.shield.userinfo.UserInfo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserInfoFileManagerTest {
  private static final Path TEST_DIR_PATH = Path.of("src", "test", "resources", "userInfos");
  private UserInfoFileManager jsonFileManager;

  @BeforeEach
  public void setUp() {
    Gson gson = new Gson();
    jsonFileManager = new UserInfoFileManager(gson, TEST_DIR_PATH);
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
    UserInfo newUser = new UserInfo("1", new Date(), new Date(), "John", "Doe", "photo.jpg", "john@example.com");
    System.out.println("Creating User: " + newUser.getId());
    jsonFileManager.create(newUser);

    // Read the user object from the file
    UserInfo retrievedUser = jsonFileManager.read(newUser.getId());
    System.out.println("Retrieved User: " + retrievedUser.getId());
    assertEquals(newUser.getId(), retrievedUser.getId());

    // Update the user object
    retrievedUser.setLastName("Smith");
    System.out.println("Updating User: " + retrievedUser.getId());
    jsonFileManager.update(retrievedUser);

    // Read the updated user object from the file
    UserInfo updatedUser = jsonFileManager.read(retrievedUser.getId());
    System.out.println("Updated User: " + updatedUser.getId());
    assertEquals(retrievedUser.getId(), updatedUser.getId());
    assertEquals("Smith", updatedUser.getLastName());

    // Delete the user
    System.out.println("Deleting User: " + updatedUser.getId());
    jsonFileManager.delete(updatedUser.getId());

    // Verify the user is deleted
    UserInfo deletedUser = jsonFileManager.read(updatedUser.getId());
    assertNull(deletedUser);
  }

  @Test
  public void testReadAll() throws IOException {
    // Create multiple users
    UserInfo user1 = new UserInfo("1", new Date(), new Date(), "John", "Doe", "photo1.jpg", "john@example.com");
    UserInfo user2 = new UserInfo("2", new Date(), new Date(), "Jane", "Smith", "photo2.jpg", "jane@example.com");
    UserInfo user3 = new UserInfo("3", new Date(), new Date(), "Jacob", "Brown", "photo3.jpg", "jacob@example.com");

    // Create users
    jsonFileManager.create(user1);
    jsonFileManager.create(user2);
    jsonFileManager.create(user3);

    // Read all users
    List<UserInfo> users = jsonFileManager.getAll();
    assertEquals(3, users.size());
  }
}
