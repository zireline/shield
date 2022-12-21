package com.splitscale.shield;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.splitscale.fordastore.core.user.User;
import com.splitscale.shield.auth.Authorization;

public class TokenizedAuthProviderTest {

  @Test
  public void shouldAnswerWithTrue() {
    Authorizer fixture = new Authorizer();

    final String username = "username";
    final String uid = "5de6477a-78a6-11ed-a1eb-0242ac120002";

    User user = new User();
    user.setUsername(username);
    user.setUid(uid);

    Authorization auth = fixture.getAuthorization(user);

    String basePK = fixture.publicKeyToBase64(auth.getPublicKey());

    final User decodedUserClaims = fixture.authorize(auth.getToken(), basePK);

    assertEquals(username, decodedUserClaims.getUsername());
    assertEquals(uid, decodedUserClaims.getUid());

    System.out.println(decodedUserClaims.getUsername());
    System.out.println(decodedUserClaims.getUid());
    System.out.println(basePK);

    System.out.println("JWT TOKEN: " + auth.getToken());
  }
}
