package com.splitscale.shield.registration;

import java.util.regex.Pattern;

public class PasswordValidator {

  public PasswordValidator() {
    // default
  }

  public boolean isValid(String password) {
    return isValidPassword(password)
        && isNotHtml(password)
        && isNotSqlInjection(password);
  }

  private boolean isValidPassword(String password) {
    String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    return password.matches(regex);
  }

  private boolean isNotHtml(String string) {
    Pattern pattern = Pattern.compile("<[^>]*>");
    return !pattern.matcher(string).find();
  }

  private boolean isNotSqlInjection(String string) {
    Pattern pattern = Pattern.compile("[\\';]+|(--)+");
    return !pattern.matcher(string).find();
  }
}
