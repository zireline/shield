package com.splitscale.shield.workflows.updateuserinfo;

import java.io.IOException;

import com.splitscale.shield.credential.update.UpdateCredentialUsernameInteractor;
import com.splitscale.shield.shielduser.ShieldUser;
import com.splitscale.shield.userinfo.UserInfo;
import com.splitscale.shield.userinfo.update.UpdateUserInfoInteractor;

public class UpdateUserInfoWorkflow {
  UpdateUserInfoInteractor updateUserInfoInteractor;
  UpdateCredentialUsernameInteractor updateCredentialInteractor;

  public UpdateUserInfoWorkflow(UpdateUserInfoInteractor updateUserInfoInteractor,
      UpdateCredentialUsernameInteractor updateCredentialInteractor) {
    this.updateUserInfoInteractor = updateUserInfoInteractor;
    this.updateCredentialInteractor = updateCredentialInteractor;
  }

  public void update(ShieldUser shieldUser) throws IOException {
    UserInfo userInfo = new UserInfo();

    userInfo.setId(shieldUser.getId());
    userInfo.setCreated(shieldUser.getCreated());
    userInfo.setEdited(shieldUser.getEdited());
    userInfo.setFirstName(shieldUser.getFirstName());
    userInfo.setLastName(shieldUser.getLastName());
    userInfo.setPhotoUrl(shieldUser.getPhotoUrl());
    userInfo.setEmail(shieldUser.getEmail());

    updateUserInfoInteractor.update(userInfo);
    updateCredentialInteractor.updateUsername(shieldUser.getId(), shieldUser.getDisplayName());
  }
}
