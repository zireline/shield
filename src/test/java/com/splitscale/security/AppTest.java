package com.splitscale.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.splitscale.security.service.SecurityService;
import com.splitscale.security.service.SecurityServiceImpl;

public class AppTest {

  @Test
  public void encodingShouldResultToSameHash() {

    SecurityService service = new SecurityServiceImpl();
    String pwd = "test1234";
    String expected = "16d7a4fca7442dda3ad93c9a726597e4";

    assertEquals(service.encode(pwd), expected);
  }
}
