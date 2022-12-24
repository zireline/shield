package com.splitscale.shield.validator;

import java.util.regex.Pattern;

import com.splitscale.fordastore.core.user.UserRequest;

public class UserRequestValidator {

  private UserRequestValidator() {
    // default constructor
  }

  public static boolean validate(UserRequest userRequest) {
    return isValidUsername(userRequest.getUsername())
        && isValidPassword(userRequest.getPassword());
  }

  private static boolean isValidPassword(String password) {
    return isValidPasswordRequirements(password)
        && isNotHtml(password)
        && isNotSqlInjection(password);
  }

  private static boolean isValidUsername(String username) {
    return isValidUsernameRequirements(username)
        && isNotHtml(username)
        && isNotSqlInjection(username);
  }

  private static boolean isValidPasswordRequirements(String password) {
    Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
    return pattern.matcher(password).find();
  }

  private static boolean isValidUsernameRequirements(String username) {
    Pattern pattern = Pattern.compile("^(?=.{3,}$)[a-zA-Z0-9._-]*[^\s]$");
    return pattern.matcher(username).find();
  }

  private static boolean isNotHtml(String password) {
    Pattern pattern = Pattern.compile("<[^>]*>");
    return !pattern.matcher(password).find();
  }

  private static boolean isNotSqlInjection(String password) {
    Pattern pattern = Pattern.compile("[\\';]+|(--)+");
    return !pattern.matcher(password).find();
  }
}
