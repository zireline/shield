package com.splitscale.shield;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.security.PublicKey;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.splitscale.fordastore.core.user.User;
import com.splitscale.shield.auth.Authorization;

@ExtendWith(value = { MockitoExtension.class })
public class AuthTest {
  @Mock
  Authorization auth;

  @Mock
  Authorizer service;

  @Mock
  PublicKey publicKey;

  @Mock
  User userClaims;

  @Test
  @DisplayName("Test getters of Authorization")
  public void AuthorizationGettersTest() {
    String SOME_TOKEN = "SOME_TOKEN";

    when(auth.getPublicKey()).thenReturn(publicKey);
    when(auth.getToken()).thenReturn(SOME_TOKEN);

    assertEquals(SOME_TOKEN, auth.getToken());
    assertEquals(publicKey, auth.getPublicKey());
  }

  @Test
  @DisplayName("Test getters of AuthService")
  public void AuthServiceGettersTest() {

    when(service.getAuthorization(userClaims)).thenReturn(auth);
    when(service.authorize("some token", "some pk")).thenReturn(userClaims);

    assertEquals(auth, service.getAuthorization(userClaims));
    assertEquals(userClaims, service.authorize("some token", "some pk"));
  }
}
