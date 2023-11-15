package com.splitscale.shield.userinfo.getall;

import java.io.IOException;
import java.util.List;

import com.splitscale.shield.repositories.UserInfoRepository;
import com.splitscale.shield.userinfo.UserInfo;

public class GetAllUserInfoInteractor {
  UserInfoRepository repository;

  public GetAllUserInfoInteractor(UserInfoRepository repository) {
    this.repository = repository;
  }

  public List<UserInfo> getAll() throws IOException {
    return repository.getAll();
  }

}
