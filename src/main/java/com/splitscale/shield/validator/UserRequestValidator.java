package com.splitscale.shield.validator;

import java.util.regex.Pattern;

import com.splitscale.fordastore.core.user.UserRequest;

public class UserRequestValidator {

  private UserRequestValidator() {
    // default constructor
  }

  public static void validate(UserRequest userRequest) throws IllegalArgumentException {
    final boolean isValid = isValidUsername(userRequest.getUsername())
        && isValidPassword(userRequest.getPassword());

    if (!isValid) {
      throw new IllegalArgumentException("Invalid username or password");
    }
  }

  /**
   * regex that allows only lowercase and uppercase letters, 0-9, dash and
   * underscore with no spaces and a minimum length of 3 chars and a maximum of 25
   */
  public static boolean isValidUsername(String username) {
    Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9_-]{3,25}$");
    return usernamePattern.matcher(username).matches();
  }

  /**
   * (?=.*[a-z]): contain at least one lowercase letter.
   * (?=.*[A-Z]): contain at least one uppercase letter.
   * (?=.*\\d): contain at least one digit.
   * [a-zA-Z\\d]: This character class matches any alphabetic or numeric
   * character.
   * {8,}: This quantifier requires the string to be at least 8 characters long.
   * (?!.*['\"\\-\\[\\]<>]): This negative lookahead group ensures that the string
   * does not contain any single quotes, double quotes, dashes, square brackets,
   * or angle brackets
   */
  public static boolean isValidPassword(String password) {
    Pattern passwordPattern = Pattern
        .compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}(?!.*['\"\\-\\[\\]<>])$");
    return passwordPattern.matcher(password).matches();
  }
}
