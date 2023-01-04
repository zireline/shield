// package com.splitscale.shield;

// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;

// import org.junit.Test;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentCaptor;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.splitscale.fordastore.core.container.ContainerRequest;
// import com.splitscale.shield.endpoints.ContainerEndpoint;

// @ExtendWith(value = { MockitoExtension.class })
// public class ContainerEndpointTest {
//   @Test
//   @DisplayName("should create container ")
//   public void testRegisterRoute() throws Exception {

//     ContainerEndpoint fakeEndpoint = mock(ContainerEndpoint.class);
//     ContainerRequest fakeRequest = mock(ContainerRequest.class);

//     ArgumentCaptor<ContainerRequest> containerRequestCaptor = ArgumentCaptor.forClass(ContainerRequest.class);
//     ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);

//     fakeEndpoint.create(fakeRequest, "khvdhvwioufvw");

//     verify(fakeEndpoint).create(containerRequestCaptor.capture(), stringCaptor.capture());
//   }
// }