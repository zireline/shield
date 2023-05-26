package com.splitscale.shield.workflows.login;

import java.io.IOException;

import com.splitscale.shield.credential.Credential;
import com.splitscale.shield.credential.CredentialRequest;
import com.splitscale.shield.credential.read.ReadCredentialInteractor;
import com.splitscale.shield.password.PasswordHasher;
import com.splitscale.shield.repositories.ObjectNotFoundException;
import com.splitscale.shield.shielduser.ShieldUser;
import com.splitscale.shield.userinfo.UserInfo;
import com.splitscale.shield.userinfo.read.ReadUserInfoInteractor;

public class LoginWorkflow {

  private ReadCredentialInteractor credentialInteractor;
  private ReadUserInfoInteractor userInfoInteractor;

  public LoginWorkflow(ReadCredentialInteractor credentialInteractor, ReadUserInfoInteractor userInfoInteractor) {
    this.credentialInteractor = credentialInteractor;
    this.userInfoInteractor = userInfoInteractor;
  }

  public ShieldUser login(CredentialRequest request) throws IOException, ObjectNotFoundException {
    Credential credential = credentialInteractor.getByUsername(request.getUsername());

    if (credential == null) {
      throw new ObjectNotFoundException("Credential " + request.getUsername() + " not found");
    }

    // get the userInfo
    UserInfo userInfo = userInfoInteractor.getById(credential.getUserId());

    if (userInfo == null) {
      throw new ObjectNotFoundException("UserInfo with id: " + credential.getUserId() + " is not found");
    }

    PasswordHasher.verify(request.getPassword(), credential.getPassword());

    final ShieldUser user = new ShieldUser();
    user.setId(userInfo.getId());
    user.setCreated(userInfo.getCreated());
    user.setEdited(userInfo.getEdited());
    user.setDisplayName(credential.getUsername());
    user.setFirstName(userInfo.getFirstName());
    user.setLastName(userInfo.getLastName());
    user.setEmail(userInfo.getEmail());
    user.setPhotoUrl(userInfo.getPhotoUrl());

    return user;
  }
}
