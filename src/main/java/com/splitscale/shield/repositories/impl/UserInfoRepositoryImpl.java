package com.splitscale.shield.repositories.impl;

import java.io.IOException;
import java.util.List;

import com.splitscale.shield.io.UserInfoFileManager;
import com.splitscale.shield.repositories.UserInfoRepository;
import com.splitscale.shield.userinfo.UserInfo;

public class UserInfoRepositoryImpl implements UserInfoRepository {

  private final UserInfoFileManager fileManager;

  public UserInfoRepositoryImpl(UserInfoFileManager fileManager) {
    this.fileManager = fileManager;
  }

  @Override
  public UserInfo getById(String id) throws IOException {
    return fileManager.getById(id);
  }

  @Override
  public List<UserInfo> getAll() throws IOException {
    return fileManager.getAll();
  }

  @Override
  public String add(UserInfo user) throws IOException {
    fileManager.create(user);
    return user.getId();
  }

  @Override
  public void update(UserInfo user) throws IOException {
    fileManager.update(user);
  }

  @Override
  public void delete(String id) throws IOException {
    fileManager.delete(id);
  }

  @Override
  public void deleteAll() throws IOException {
    fileManager.deleteAll();
  }

}
