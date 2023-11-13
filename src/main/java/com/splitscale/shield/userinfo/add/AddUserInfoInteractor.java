package com.splitscale.shield.userinfo.add;

import java.io.IOException;

import com.splitscale.shield.repositories.UserInfoRepository;
import com.splitscale.shield.userinfo.UserInfo;

public class AddUserInfoInteractor {
  UserInfoRepository repository;

  public AddUserInfoInteractor(UserInfoRepository repository) {
    this.repository = repository;
  }

  public void add(UserInfo userInfo) throws IOException {
    repository.add(userInfo);
  }
}
