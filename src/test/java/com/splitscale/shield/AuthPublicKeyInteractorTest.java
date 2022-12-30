package com.splitscale.shield;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;

import com.splitscale.fordastore.core.user.User;
import com.splitscale.shield.auth.AuthPublicKeyInteractor;

public class AuthPublicKeyInteractorTest {
  @Test
  public void testGenerateJwtFromUser() throws IOException {

    AuthPublicKeyInteractor interactor = mock(AuthPublicKeyInteractor.class);
    User user = mock(User.class);

    String someJwt = "someJwt";

    when(interactor.generateJwtFromUser(user)).thenReturn(someJwt);

    assertEquals(someJwt, interactor.generateJwtFromUser(user));
  }
}
