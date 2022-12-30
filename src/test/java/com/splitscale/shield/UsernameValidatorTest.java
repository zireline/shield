package com.splitscale.shield;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import com.splitscale.shield.validator.UserRequestValidator;

public class UsernameValidatorTest {
  String username;

  @Before
  public void setUp() {
    username = "username";
  }

  @BeforeEach
  public void reset() {
    username = "username";
  }

  @Test
  @DisplayName("should return true if username is clean")
  public void shouldAcceptNormalUsername() {
    assertTrue(UserRequestValidator.isValidUsername(username));
  }

  @Test
  @DisplayName("should return false if username is empty")
  public void testIfUsernameIsEmpty() {
    username = " ";

    assertFalse(UserRequestValidator.isValidUsername(username));
  }

  @Test
  @DisplayName("should return false if username has space")
  public void testIfUsernameHasSpace() {
    username = "naruto uzumaki";

    assertFalse(UserRequestValidator.isValidUsername(username));
  }

  @Test
  @DisplayName("should return false if username is html code")
  public void testIfUsernameIsHtml() {
    username = "<b>Jerome</b>";
    assertFalse(UserRequestValidator.isValidUsername(username));
  }

  @Test
  @DisplayName("should return false if username is sql injection")
  public void testIfUsernameIsSqlInjection() {
    username = "'; DROP TABLE users --";
    assertFalse(UserRequestValidator.isValidUsername(username));
  }
}
