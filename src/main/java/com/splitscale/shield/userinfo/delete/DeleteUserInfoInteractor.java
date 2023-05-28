package com.splitscale.shield.userinfo.delete;

import java.io.IOException;

import com.splitscale.shield.repositories.UserInfoRepository;

public class DeleteUserInfoInteractor {
  private UserInfoRepository repository;

  public DeleteUserInfoInteractor(UserInfoRepository repository) {
    this.repository = repository;
  }

  void delete(String id) throws IOException {
    repository.delete(id);
  }
}
