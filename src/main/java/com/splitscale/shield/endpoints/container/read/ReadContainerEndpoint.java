package com.splitscale.shield.endpoints.container.read;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.splitscale.fordastore.core.container.Container;
import com.splitscale.fordastore.core.container.ContainerResponse;
import com.splitscale.fordastore.core.container.read.ReadContainerInteractor;
import com.splitscale.shield.jws.ShieldJws;

public class ReadContainerEndpoint {
  private ReadContainerInteractor readContainerInteractor;

  public ReadContainerEndpoint(ReadContainerInteractor readContainerInteractor) {
    this.readContainerInteractor = readContainerInteractor;
  }

  public List<ContainerResponse> readListByUid(String uid, String jwsToken)
      throws IOException, GeneralSecurityException {

    ShieldJws.validateJws(jwsToken);

    List<ContainerResponse> responses = new ArrayList<ContainerResponse>();
    List<Container> containers = readContainerInteractor.readListByUid(uid);

    for (Container container : containers) {
      ContainerResponse containerResponse = new ContainerResponse();
      containerResponse.setContainerID(container.getContainerID());
      containerResponse.setName(container.getName());

      responses.add(containerResponse);
    }

    return responses;
  }

  public List<ContainerResponse> readListByName(String name, String jwsToken)
      throws IOException, GeneralSecurityException {
    ShieldJws.validateJws(jwsToken);

    List<ContainerResponse> responses = new ArrayList<ContainerResponse>();
    List<Container> containers = readContainerInteractor.readListByName(name);

    for (Container container : containers) {
      ContainerResponse containerResponse = new ContainerResponse();
      containerResponse.setContainerID(container.getContainerID());
      containerResponse.setName(container.getName());

      responses.add(containerResponse);
    }

    return responses;
  }

  public ContainerResponse readByContainerId(Long containerId, String jwsToken)
      throws IOException, GeneralSecurityException {
    ShieldJws.validateJws(jwsToken);

    Container container = readContainerInteractor.getByContainerId(containerId);

    ContainerResponse containerResponse = new ContainerResponse();
    containerResponse.setContainerID(container.getContainerID());
    containerResponse.setName(container.getName());

    return containerResponse;
  }
}
