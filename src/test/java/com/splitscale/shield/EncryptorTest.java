package com.splitscale.shield;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;

import com.splitscale.shield.password.PasswordHasher;

public class EncryptorTest {

  @Test
  public void encodingShouldResultToSameHash() {
    String pwd = "password";
    String hashedPwd = PasswordHasher.encrypt(pwd);

    assertDoesNotThrow(() -> PasswordHasher.verify(pwd, hashedPwd));
  }
}
