package com.splitscale.shield;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.splitscale.shield.sanitizer.UsernameValidator;

public class UsernameValidatorTest {
  UsernameValidator fixture;

  @Before
  public void setUp() {
    fixture = new UsernameValidator();
  }

  @Test
  @DisplayName("should return true if username is clean")
  public void shouldAcceptNormalPassword() {
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
    String username = "<b>JohnDoe</b>";

    assertFalse(fixture.isValid(username));
  }

   @Test
  @DisplayName("should return false if username is sql injection")
  public void testIfUsernameIsSqlInjection() {
    String username = "'; DROP TABLE users --";

    assertFalse(fixture.isValid(username));
  }
}

  
  
