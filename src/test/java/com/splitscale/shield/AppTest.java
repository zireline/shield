package com.splitscale.shield;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

  Sekyur sekyur;

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
