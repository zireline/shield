package com.splitscale.shield.workflows.register;

import com.splitscale.shield.credential.Credential;
import com.splitscale.shield.credential.CredentialRequest;
import com.splitscale.shield.credential.add.AddCredentialInteractor;
import com.splitscale.shield.credential.read.ReadCredentialInteractor;
import com.splitscale.shield.password.PasswordHasher;
import com.splitscale.shield.userinfo.UserInfo;
import com.splitscale.shield.userinfo.add.AddUserInfoInteractor;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class RegisterWorkflow {

  private AddCredentialInteractor addCredentialInteractor;
  private ReadCredentialInteractor readCredentialInteractor;
  private AddUserInfoInteractor addUserInfoInteractor;

  public RegisterWorkflow(AddCredentialInteractor addCredentialInteractor,
      ReadCredentialInteractor readCredentialInteractor, AddUserInfoInteractor addUserInfoInteractor) {
    this.addCredentialInteractor = addCredentialInteractor;
    this.readCredentialInteractor = readCredentialInteractor;
    this.addUserInfoInteractor = addUserInfoInteractor;
  }

  public void register(CredentialRequest request) throws IOException, IllegalArgumentException {
    Credential existingCredential = readCredentialInteractor.getByUsername(request.getUsername());

    if (existingCredential != null) {
      throw new IllegalArgumentException("credential with username: " + request.getUsername() + " already exists");
    }

    String credentialId = UUID.randomUUID().toString();
    String userInfoId = UUID.randomUUID().toString();
    String encryptedPassword = PasswordHasher.encrypt(request.getPassword());

    Credential credential = new Credential(credentialId, userInfoId, request.getUsername(), encryptedPassword);
    UserInfo user = new UserInfo(userInfoId, new Date(), new Date(), null, null, null,
        null);

    addUserInfoInteractor.add(user);
    addCredentialInteractor.add(credential);
  }
}
