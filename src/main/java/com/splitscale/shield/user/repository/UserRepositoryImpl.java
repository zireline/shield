package com.splitscale.shield.user.repository;

import java.io.IOException;
import java.util.List;

import com.splitscale.shield.io.JsonFileManager;
import com.splitscale.shield.user.User;

public class UserRepositoryImpl implements UserRepository {
  private final JsonFileManager jsonFileManager;

  public UserRepositoryImpl(JsonFileManager jsonFileManager) {
    this.jsonFileManager = jsonFileManager;
  }

  @Override
  public User getById(String id) throws IOException {
    return jsonFileManager.read(id);
  }

  @Override
  public User[] getAll() throws IOException {
    List<User> userList = jsonFileManager.getAll();
    return userList.toArray(new User[0]);

  }

  @Override
  public User getByUsername(String username) throws IOException, ObjectNotFoundException {

    List<User> userList = jsonFileManager.getAll();

    for (User user : userList) {
      if (username.equals(user.getUsername())) {
        return user;
      }
    }

    throw new ObjectNotFoundException("User with Username: " + username + " not found");
  }

  @Override
  public String add(User user) throws IOException {
    jsonFileManager.create(user);
    return user.getId();
  }

  @Override
  public void update(User user) throws IOException {
    jsonFileManager.update(user);
  }

  @Override
  public void delete(String id) throws IOException {
    User user = jsonFileManager.read(id);

    if (user != null) {
      jsonFileManager.delete(user);
    }
  }
}
