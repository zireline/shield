package com.splitscale.shield;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.splitscale.fordastore.core.auth.Authorization;
import com.splitscale.fordastore.core.user.User;
import com.splitscale.shield.auth.Authorizer;
import com.splitscale.shield.auth.PublicKeyConverter;

public class AuthorizerTest {

  @Test
  public void shouldShowDefaultData() throws Exception {
    final String username = "username";
    final String uid = "5de6477a-78a6-11ed-a1eb-0242ac120002";

    User user = new User();
    user.setUsername(username);
    user.setUid(uid);

    Authorization auth = Authorizer.getAuthorization(user);

    String basePK = PublicKeyConverter.publicKeyToBase64(auth.getPublicKey());

    final User decodedUserClaims = Authorizer.authorize(auth.getJwt(), basePK);

    assertEquals(username, decodedUserClaims.getUsername());
    assertEquals(uid, decodedUserClaims.getUid());

    System.out.println(decodedUserClaims.getUsername());
    System.out.println(decodedUserClaims.getUid());
    System.out.println(basePK);

    System.out.println("JWT TOKEN: " + auth.getJwt());
  }

  @Test
  public void shouldAuthorize() throws GeneralSecurityException {
    final String username = "username";
    final String uid = "5de6477a-78a6-11ed-a1eb-0242ac120002";

    User freshUser = new User();
    freshUser.setUsername(username);
    freshUser.setUid(uid);

    Authorization auth = Authorizer.getAuthorization(freshUser);

    String base64Pk = PublicKeyConverter.publicKeyToBase64(auth.getPublicKey());

    User user = Authorizer.authorize(auth.getJwt(), base64Pk);

    assertNotNull(user);

    System.out.println(user.getUsername());
    System.out.println(user.getUid());
  }

  @Test
  public void shouldNotAuthorize() throws GeneralSecurityException, InterruptedException {
    final String username = "username";
    final String uid = "5de6477a-78a6-11ed-a1eb-0242ac120002";

    User freshUser = new User();
    freshUser.setUsername(username);
    freshUser.setUid(uid);

    Authorization correctAuth = Authorizer.getAuthorization(freshUser);

    TimeUnit.MILLISECONDS.sleep(3000);

    Authorization wrongAuth = Authorizer.getAuthorization(freshUser);

    String base64Pk = PublicKeyConverter.publicKeyToBase64(correctAuth.getPublicKey());

    assertThrows(Exception.class, () -> Authorizer.authorize(wrongAuth.getJwt(), base64Pk));
  }
}
