package com.splitscale.shield;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import com.splitscale.shield.validator.UserRequestValidator;

public class PasswordValidatorTest {
  String password;

  @Before
  public void setUp() {
    password = "Password1234";
  }

  @BeforeEach
  public void reset() {
    password = "Password1234";
  }

  @Test
  @DisplayName("should pass if password is clean")
  public void shouldAcceptNormalPassword() {
    assertTrue(UserRequestValidator.isValidPassword(password));
  }

  @Test
  @DisplayName("should return false if password is under a specified length")
  public void shouldBeAtMost8Characters() {
    password = "Forda12";

    assertFalse(UserRequestValidator.isValidPassword(password));
  }

  @Test
  @DisplayName("should return false if password does not contain lowercase letter")
  public void testIfPasswordDoesNotContainLowerCase() {
    password = "FORDASTORE123";

    assertFalse(UserRequestValidator.isValidPassword(password));
  }

  @Test
  @DisplayName("should return false if password does not contain uppercase letter")
  public void testIfPasswordDoesNotContainUppercase() {
    password = "fordastore123";

    assertFalse(UserRequestValidator.isValidPassword(password));
  }

  @Test
  @DisplayName("should return false if password does not contain a number")
  public void testIfPasswordDoesNotContainANumber() {
    password = "Fordastore";

    assertFalse(UserRequestValidator.isValidPassword(password));
  }

  @Test
  @DisplayName("should return false if password is html code")
  public void testIfPasswordIsHtml() {
    password = "<b>Jerome</b>";

    assertFalse(UserRequestValidator.isValidPassword(password));
  }

  @Test
  @DisplayName("should return false if password is sql injection")
  public void testIfPasswordIsSqlInjection() {
    password = "'; DROP TABLE users --";

    assertFalse(UserRequestValidator.isValidPassword(password));
  }
}
