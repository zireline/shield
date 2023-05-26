package com.splitscale.shield.repositories;

import java.io.IOException;
import java.util.List;

import com.splitscale.shield.userinfo.UserInfo;

public interface UserInfoRepository {
  public UserInfo getById(String id) throws IOException;

  public List<UserInfo> getAll() throws IOException;

  public String add(UserInfo user) throws IOException;

  public void update(UserInfo user) throws IOException;

  public void delete(String id) throws IOException;

  public void deleteAll() throws IOException;
}
