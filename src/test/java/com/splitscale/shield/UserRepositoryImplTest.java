package com.splitscale.shield;

import com.google.gson.Gson;
import com.splitscale.shield.io.JsonFileManager;
import com.splitscale.shield.user.User;
import com.splitscale.shield.user.repository.ObjectNotFoundException;
import com.splitscale.shield.user.repository.UserRepository;
import com.splitscale.shield.user.repository.UserRepositoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryImplTest {
  private UserRepository userRepository;
  private static final Path TEST_DIR_PATH = Path.of("src", "test", "resources", "users");

  @BeforeEach
  public void setUp() throws IOException {
    Gson gson = new Gson();
    JsonFileManager jsonFileManager = new JsonFileManager(gson, TEST_DIR_PATH);
    userRepository = new UserRepositoryImpl(jsonFileManager);
  }

  @Test
  public void testCRUDOperations() throws IOException, ObjectNotFoundException {
    // Create a new user
    User newUser = new User("1", "jerome", "password");
    userRepository.add(newUser);

    // Get user by ID
    User retrievedUser = userRepository.getById(newUser.getId());
    assertNotNull(retrievedUser);
    assertEquals(newUser.getUsername(), retrievedUser.getUsername());

    // Get user by username
    User retrievedByUsername = userRepository.getByUsername(newUser.getUsername());
    assertNotNull(retrievedByUsername);
    assertEquals(newUser.getId(), retrievedByUsername.getId());

    // Update user
    retrievedUser.setPassword("newpassword");
    userRepository.update(retrievedUser);

    // Verify updated user
    User updatedUser = userRepository.getById(retrievedUser.getId());
    assertNotNull(updatedUser);
    assertEquals("newpassword", updatedUser.getPassword());

    // Delete user
    userRepository.delete(updatedUser.getId());

    // Verify user is deleted
    User deletedUser = userRepository.getById(updatedUser.getId());
    assertNull(deletedUser);
  }
}
