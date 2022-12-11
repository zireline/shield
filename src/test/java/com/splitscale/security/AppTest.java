package com.splitscale.security;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.splitscale.fordastore.core.security.EncryptionService;

public class AppTest {

  EncryptionService sekyur;

  @Before
  public void setUp() {
    sekyur = new Sekyur();
  }

  @Test
  public void encodingShouldResultToSameHash() {
    String pwd = "password";
    String hashedPwd = sekyur.encrypt(pwd);

    assertTrue(sekyur.verify(pwd, hashedPwd));
  }
}
