package com.splitscale.shield.endpoints.users.get;

import java.io.IOException;
import java.util.List;

import com.splitscale.shield.userinfo.UserInfo;
import com.splitscale.shield.workflows.updateuserinfo.GetAllUserInfoWorkflow;

public class GetAllUserInfo {
  private GetAllUserInfoWorkflow workflow;

  public GetAllUserInfo() {
  }

  public GetAllUserInfo(GetAllUserInfoWorkflow workflow) {
    this.workflow = workflow;
  }

  public List<UserInfo> getALl() throws IOException {
    return workflow.getAll();
  }
}
