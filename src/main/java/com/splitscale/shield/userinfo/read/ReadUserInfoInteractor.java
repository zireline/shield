package com.splitscale.shield.userinfo.read;

import java.io.IOException;

import com.splitscale.shield.repositories.UserInfoRepository;
import com.splitscale.shield.userinfo.UserInfo;

public class ReadUserInfoInteractor {
  UserInfoRepository repository;

  public ReadUserInfoInteractor(UserInfoRepository repository) {
    this.repository = repository;
  }

  public UserInfo getById(String userId) throws IOException {
    return repository.getById(userId);
  }

}
