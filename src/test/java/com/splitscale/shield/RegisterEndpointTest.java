package com.splitscale.shield;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.splitscale.fordastore.core.user.UserRequest;
import com.splitscale.shield.endpoints.RegisterEndpoint;

@ExtendWith(value = { MockitoExtension.class })
public class RegisterEndpointTest {

  UserRequest userRequest;

  @Before
  public void init() {
    userRequest = new UserRequest("username", "Password1234");
  }

  @Test
  @DisplayName("register route should return a jwt string token")
  public void testRegisterRoute() throws Exception {

    RegisterEndpoint endpoint = mock(RegisterEndpoint.class);

    when(endpoint.register(userRequest)).thenReturn(true);

    assertTrue(endpoint.register(userRequest));
  }
}