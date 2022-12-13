package com.splitscale.shield;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.splitscale.fordastore.core.security.Authorization;
import com.splitscale.fordastore.core.security.UserClaims;

public class TokenizedAuthProviderTest {

  TokenizedAuthProvider service;

  @Before
  public void setUp() {
    service = new TokenizedAuthProvider();
  }

  @Test
  public void shouldAnswerWithTrue() {
    final String username = "username";
    final String uid = "5de6477a-78a6-11ed-a1eb-0242ac120002";
    final Long id = 4234l;

    final UserClaims claims = new UserClaims(username, id, uid);

    Authorization auth = service.getAuthorization(claims);

    String basePK = service.publicKeyToBase64(auth.getPublicKey());

    final UserClaims decodedUserClaims = service.authorize(auth.getToken(), basePK);

    assertEquals(username, decodedUserClaims.getUsername());
    assertEquals(uid, decodedUserClaims.getUid());
    assertEquals(id, decodedUserClaims.getId());

    System.out.println(decodedUserClaims.getUsername());
    System.out.println(decodedUserClaims.getUid());
    System.out.println(decodedUserClaims.getId());
    System.out.println(basePK);
  }
}
