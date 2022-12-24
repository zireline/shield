package com.splitscale.shield;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.splitscale.shield.encryption.Encryptor;

public class EncryptorTest {

  @Test
  public void encodingShouldResultToSameHash() {
    String pwd = "password";
    String hashedPwd = Encryptor.encrypt(pwd);

    assertTrue(Encryptor.verify(pwd, hashedPwd));
  }
}
