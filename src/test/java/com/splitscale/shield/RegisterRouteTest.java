package com.splitscale.shield;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.shield.routes.RegisterRoute;

@ExtendWith(value = { MockitoExtension.class })
public class RegisterRouteTest {
  @Mock
  RegisterRoute fixture;

  @Mock
  UserRequest userRequest;

  @Before
  public void init() {
    fixture = new RegisterRoute(null, null);
  }

  @Test
  @DisplayName("register route should return a jwt string token")
  public void testRegisterRoute() throws Exception {
    String someToken = "some token";

    when(fixture.register(userRequest)).thenReturn(someToken);

    assertEquals(someToken, fixture.register(userRequest));
  }
}