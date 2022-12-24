package com.splitscale.shield.encryption;

import com.password4j.Hash;
import com.password4j.Password;

public class Encryptor {

  private Encryptor() {
    // default implementation
  }

  public static String encrypt(String pwd) {
    Hash hash = Password.hash(pwd).addRandomSalt(12).withArgon2();

    return hash.getResult();
  }

  public static boolean verify(String pwd, String hashedPwd) {
    return Password.check(pwd, hashedPwd).withArgon2();
  }
}
