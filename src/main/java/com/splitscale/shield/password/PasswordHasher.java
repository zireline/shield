package com.splitscale.shield.password;

import com.password4j.Hash;
import com.password4j.Password;

public class PasswordHasher {

  private PasswordHasher() {
    // default implementation
  }

  public static String encrypt(String pwd) {
    Hash hash = Password.hash(pwd).addRandomSalt(12).withArgon2();

    return hash.getResult();
  }

  public static void verify(String pwd, String hashedPwd) throws IllegalArgumentException {
    boolean isMatched = Password.check(pwd, hashedPwd).withArgon2();

    if (!isMatched) {
      throw new IllegalArgumentException("Wrong username or password");
    }
  }
}
