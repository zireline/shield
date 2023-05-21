package com.splitscale.shield.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.splitscale.shield.Shield;
import com.splitscale.shield.jwt.validate.ValidJwtResponse;
import com.splitscale.shield.login.LoginResponse;
import com.splitscale.shield.user.UserRequest;
import com.splitscale.shield.user.repository.ObjectNotFoundException;

@SpringBootTest
public class ShieldTest {

  @Autowired
  private Shield shield;

  @Test
  public void testRegisterUser() throws InvalidKeyException, IOException {
    // Prepare test data
    UserRequest request = new UserRequest("joejoe", "password");

    // Verify that no exceptions are thrown
    assertDoesNotThrow(() -> shield.registerUser(request));
  }

  @Test
  public void testLoginUser() throws InvalidKeyException, IOException, ObjectNotFoundException {
    // Prepare test data
    UserRequest request = new UserRequest("joejoe", "password");

    // Verify that no exceptions are thrown
    assertDoesNotThrow(() -> System.out.println(shield.loginUser(request).getToken()));
  }

  @Test
  public void testValidateJwt() throws IOException, ObjectNotFoundException, GeneralSecurityException {
    // Prepare test data
    UserRequest request = new UserRequest("joejoe", "password");

    LoginResponse response = shield.loginUser(request);

    // Verify that no exceptions are thrown
    ValidJwtResponse validJwt = shield.validateJwt(response.getToken(), response.getUserResponse().getId());
    assertNotNull(validJwt);

    System.out.println(validJwt.getToken());

    System.out.println(validJwt.getClaims().getId());
    System.out.println(validJwt.getClaims().getAudience());
    System.out.println(validJwt.getClaims().getIssuer());
  }

  @Test
  public void testInValidateJwt()
      throws InvalidKeyException, IOException, ObjectNotFoundException, GeneralSecurityException {
    // Prepare test data
    UserRequest request = new UserRequest("joejoe", "password");

    LoginResponse response = shield.loginUser(request);

    String invalidJwt = shield.inValidateJwt(response.getToken());
    assertNotNull(invalidJwt);

    System.out.println("Invalid Jwt: " + invalidJwt);
  }
}