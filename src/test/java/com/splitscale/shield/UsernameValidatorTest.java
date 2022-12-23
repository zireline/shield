package com.splitscale.shield;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.splitscale.shield.registration.UsernameValidator;

public class UsernameValidatorTest {
  UsernameValidator fixture;

  @Before
  public void setUp() {
    fixture = new UsernameValidator();
  }

  @Test
  @DisplayName("should return true if username is clean")
  public void shouldAcceptNormalUsername() {
    String username = "username";

    assertTrue(fixture.isValid(username));
  }

  @Test
  @DisplayName("should return false if username is empty")
  public void testIfUsernameIsEmpty() {
    String username = " ";

    assertFalse(fixture.isValid(username));
  }

  @Test
  @DisplayName("should return false if username has space")
  public void testIfUsernameHasSpace() {
    String username = "naruto uzumaki";

    assertFalse(fixture.isValid(username));
  }

  @Test
  @DisplayName("should return false if username is html code")
  public void testIfUsernameIsHtml() {
    String username = "<b>Jerome</b>";

    assertFalse(fixture.isValid(username));
  }

  @Test
  @DisplayName("should return false if username is sql injection")
  public void testIfUsernameIsSqlInjection() {
    String username = "'; DROP TABLE users --";

    assertFalse(fixture.isValid(username));
  }

  @Test
  @DisplayName("should return false if username Exceeds specified length")
  public void shouldReturnFalseIfLengthExceedsMaxLength() {
    String username = "usernameadkjwnhbsasd";

    assertFalse(fixture.isValid(username));
  }
}
