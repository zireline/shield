package com.splitscale.shield.workflows.updateuserinfo;

import java.io.IOException;
import java.util.List;

import com.splitscale.shield.userinfo.UserInfo;
import com.splitscale.shield.userinfo.getall.GetAllUserInfoInteractor;

public class GetAllUserInfoWorkflow {
  GetAllUserInfoInteractor getAllUserInfoInteractor;

  public GetAllUserInfoWorkflow() {
  }

  public GetAllUserInfoWorkflow(GetAllUserInfoInteractor getAllUserInfoInteractor) {
    this.getAllUserInfoInteractor = getAllUserInfoInteractor;
  }

  public List<UserInfo> getAll() throws IOException {
    return getAllUserInfoInteractor.getAll();
  }
}
