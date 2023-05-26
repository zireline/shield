package com.splitscale.shield.userinfo.add;

import java.io.IOException;

import com.splitscale.shield.repositories.UserInfoRepository;
import com.splitscale.shield.userinfo.UserInfo;

public class AddUserInfoInteractor {
  UserInfoRepository repository;

  public AddUserInfoInteractor(UserInfoRepository repository) {
    this.repository = repository;
  }

  public String add(UserInfo userInfo) throws IOException {
    return repository.add(userInfo);
  }
}
