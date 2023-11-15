package com.splitscale.shield.endpoints.users.update;

import java.io.IOException;

import com.splitscale.shield.shielduser.ShieldUser;
import com.splitscale.shield.workflows.updateuserinfo.UpdateUserInfoWorkflow;

public class UpdateUserInfo {
  private UpdateUserInfoWorkflow workflow;

  public UpdateUserInfo(UpdateUserInfoWorkflow workflow) {
    this.workflow = workflow;
  }

  public void update(ShieldUser shieldUser) throws IOException {
    workflow.update(shieldUser);
  }
}
