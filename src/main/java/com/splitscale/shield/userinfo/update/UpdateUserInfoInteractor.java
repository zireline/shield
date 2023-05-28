package com.splitscale.shield.userinfo.update;

import java.io.IOException;

import com.splitscale.shield.repositories.UserInfoRepository;
import com.splitscale.shield.userinfo.UserInfo;

public class UpdateUserInfoInteractor {
  private UserInfoRepository repository;

  public UpdateUserInfoInteractor(UserInfoRepository repository) {
    this.repository = repository;
  }

  public void update(UserInfo user) throws IOException {
    repository.update(user);
  }
}
