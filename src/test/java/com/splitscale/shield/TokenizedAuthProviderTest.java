package com.splitscale.shield;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.splitscale.fordastore.core.user.User;
import com.splitscale.shield.auth.Authorization;

public class TokenizedAuthProviderTest {

  Authorizer fixture;

  @Before
  public void setUp() {
    fixture = new Authorizer();
  }

  @Test
  public void shouldAnswerWithTrue() {
    final String username = "username";
    final String uid = "5de6477a-78a6-11ed-a1eb-0242ac120002";
    final Long id = 4234l;

    final User claims = new User();

    Authorization auth = fixture.getAuthorization(claims);

    String basePK = fixture.publicKeyToBase64(auth.getPublicKey());

    final User decodedUserClaims = fixture.authorize(auth.getToken(), basePK);

    assertEquals(username, decodedUserClaims.getUsername());
    assertEquals(uid, decodedUserClaims.getUid());
    assertEquals(id, decodedUserClaims.getId());

    System.out.println(decodedUserClaims.getUsername());
    System.out.println(decodedUserClaims.getUid());
    System.out.println(decodedUserClaims.getId());
    System.out.println(basePK);
  }
}
