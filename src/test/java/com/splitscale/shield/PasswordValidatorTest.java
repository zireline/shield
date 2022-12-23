package com.splitscale.shield;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.splitscale.shield.registration.PasswordValidator;

public class PasswordValidatorTest {
  PasswordValidator fixture;

  @Before
  public void setUp() {
    fixture = new PasswordValidator();
  }

  @Test
  @DisplayName("should return true if password is clean")
  public void shouldAcceptNormalPassword() {
    String password = "Fordastore123";

    assertTrue(fixture.isValid(password));
  }

  @Test
  @DisplayName("should return false if password is under a specified length")
  public void shouldBeAtMost8Characters() {
    String password = "Forda12";

    assertFalse(fixture.isValid(password));
  }

  @Test
  @DisplayName("should return false if password does not contain lowercase letter")
  public void testIfPasswordDoesNotContainLowerCase() {
    String username = "FORDASTORE123";

    assertFalse(fixture.isValid(username));
  }

  @Test
  @DisplayName("should return false if password does not contain uppercase letter")
  public void testIfPasswordDoesNotContainUppercase() {
    String username = "fordastore123";

    assertFalse(fixture.isValid(username));
  }

  @Test
  @DisplayName("should return false if password does not contain a number")
  public void testIfPasswordDoesNotContainANumber() {
    String username = "Fordastore";

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
}
