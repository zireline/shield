package com.splitscale.shield;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import com.splitscale.shield.endpoints.LogoutEndpoint;

@ExtendWith(value = { MockitoExtension.class })
public class LogoutEndpointTest {
  @Test
  @DisplayName("should delete public key in auth repository ")
  public void logout() throws Exception {

    LogoutEndpoint fakeEndpoint = mock(LogoutEndpoint.class);

    ArgumentCaptor<String> stringInput = ArgumentCaptor.forClass(String.class);

    fakeEndpoint.logout("AQ12");

    verify(fakeEndpoint).logout(stringInput.capture());
  }
}