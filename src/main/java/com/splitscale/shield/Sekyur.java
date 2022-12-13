package com.splitscale.shield;

import com.password4j.Hash;
import com.password4j.Password;
import com.splitscale.fordastore.core.security.EncryptionService;

public class Sekyur {

  public Sekyur() {
    // default implementation
  }

  public String encrypt(String pwd) {
    Hash hash = Password.hash(pwd).addRandomSalt(12).withArgon2();

    return hash.getResult();
  }

  public boolean verify(String pwd, String hashedPwd) {
    return Password.check(pwd, hashedPwd).withArgon2();
  }
}
