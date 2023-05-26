package com.splitscale.shield;

import com.google.gson.Gson;
import com.splitscale.shield.credential.Credential;
import com.splitscale.shield.io.CredentialFileManager;
import com.splitscale.shield.repositories.CredentialRepository;
import com.splitscale.shield.repositories.ObjectNotFoundException;
import com.splitscale.shield.repositories.impl.CredentialRepositoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CredentialRepositoryImplTest {
  private CredentialRepository credentialRepository;
  private static final Path TEST_DIR_PATH = Path.of("src", "test", "resources", "credentials");

  @BeforeEach
  public void setUp() throws IOException {
    Gson gson = new Gson();
    CredentialFileManager jsonFileManager = new CredentialFileManager(gson, TEST_DIR_PATH);
    credentialRepository = new CredentialRepositoryImpl(jsonFileManager);
  }

  @Test
  public void testCRUDOperations() throws IOException, ObjectNotFoundException {
    // Create a new user
    Credential credential = new Credential("1", "userId", "jerome", "password");
    credentialRepository.add(credential);

    // Get user by ID
    Credential retrievedCredential = credentialRepository.getById(credential.getId());
    assertNotNull(retrievedCredential);
    assertEquals(credential.getUsername(), retrievedCredential.getUsername());

    // Get user by username
    Credential retrievedByUsername = credentialRepository.getByUsername(credential.getUsername());
    assertNotNull(retrievedByUsername);
    assertEquals(credential.getId(), retrievedByUsername.getId());

    // Update user
    retrievedCredential.setPassword("newpassword");
    credentialRepository.update(retrievedCredential);

    // Verify updated user
    Credential updatedCredential = credentialRepository.getById(retrievedCredential.getId());
    assertNotNull(updatedCredential);
    assertEquals("newpassword", updatedCredential.getPassword());

    // Delete user
    credentialRepository.delete(updatedCredential.getId());

    // Verify user is deleted
    Credential deletedUser = credentialRepository.getById(updatedCredential.getId());
    assertNull(deletedUser);
  }
}
