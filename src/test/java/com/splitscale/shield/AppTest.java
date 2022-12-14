package com.splitscale.shield;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

  Encryptor fixture;

  @Before
  public void setUp() {
    fixture = new Encryptor();
  }

  @Test
  public void encodingShouldResultToSameHash() {
    String pwd = "password";
    String hashedPwd = fixture.encrypt(pwd);

    assertTrue(fixture.verify(pwd, hashedPwd));
  }
}
