package com.splitscale.shield;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.splitscale.fordastore.core.container.Container;
import com.splitscale.fordastore.core.container.ContainerRequest;
import com.splitscale.shield.endpoints.ContainerEndpoint;

@ExtendWith(value = { MockitoExtension.class })
public class ContainerEndpointTest {
  @Test
  @DisplayName("should create container ")
  public void testRegisterRoute() throws Exception {

    ContainerEndpoint fakeEndpoint = mock(ContainerEndpoint.class);
    ContainerRequest fakeRequest = mock(ContainerRequest.class);
    Container expectedContainer = mock(Container.class);

    when(fakeEndpoint.create(fakeRequest, "khvdhvwioufvw")).thenReturn(expectedContainer);

    assertEquals(expectedContainer, fakeEndpoint.create(fakeRequest, "khvdhvwioufvw"));
  }
}