package com.splitscale.shield;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mock;

import com.splitscale.fordastore.core.repositories.AuthRepository;
import com.splitscale.fordastore.core.user.User;
import com.splitscale.shield.jwt.JwtInteractor;

public class JwtInteractorTest {
  @Mock
  private AuthRepository repository;

  @Test
  public void testGenerateJwtFromUser() throws IOException {

    JwtInteractor interactor = mock(JwtInteractor.class);
    User user = mock(User.class);

    String someJwt = "someJwt";

    when(interactor.generateJwtFromUser(user)).thenReturn(someJwt);

    assertEquals(someJwt, interactor.generateJwtFromUser(user));
  }
}
