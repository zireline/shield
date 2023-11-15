package com.splitscale.shield.workflows.updateuserinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.splitscale.shield.credential.Credential;
import com.splitscale.shield.credential.read.ReadAllCredentialInteractor;
import com.splitscale.shield.userinfo.UserInfo;
import com.splitscale.shield.userinfo.getall.GetAllUserInfoInteractor;

public class GetAllUserInfoWorkflow {
  GetAllUserInfoInteractor getAllUserInfoInteractor;
  ReadAllCredentialInteractor readAllCredentialInteractor;

  public GetAllUserInfoWorkflow() {
  }

  public GetAllUserInfoWorkflow(GetAllUserInfoInteractor getAllUserInfoInteractor,
      ReadAllCredentialInteractor readAllCredentialInteractor) {
    this.getAllUserInfoInteractor = getAllUserInfoInteractor;
    this.readAllCredentialInteractor = readAllCredentialInteractor;
  }

  public List<UserInfo> getAll() throws IOException {
    List<UserInfo> userInfos = getAllUserInfoInteractor.getAll();
    List<Credential> credentials = readAllCredentialInteractor.getAllCredentials();

    for (UserInfo userInfo : userInfos) {
      // Find the corresponding Credential for the current UserInfo
      Credential credential = findCredentialById(credentials, userInfo.getId());

      // Update UserInfo with values from Credential
      if (credential != null) {
        userInfo.setFirstName(credential.getUsername());
        // Update other fields as needed
      }
    }

    return userInfos;
  }

  private Credential findCredentialById(List<Credential> credentials, String userId) {
    for (Credential credential : credentials) {
      if (userId.matches(credential.getUserId())) {
        return credential;
      }
    }
    return null;
  }
}
