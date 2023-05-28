package com.splitscale.shield.userinfo.delete;

import java.io.IOException;

import com.splitscale.shield.repositories.UserInfoRepository;

public class DeleteAllUserInfoInteractor {
  private UserInfoRepository repository;

  public DeleteAllUserInfoInteractor(UserInfoRepository repository) {
    this.repository = repository;
  }

  void deleteAll() throws IOException {
    repository.deleteAll();
  }
}
