package com.splitscale.shield.credential;

public class Credential {

  private String id;
  private String userId;
  private String username;
  private String password;

  public Credential() {
    // Default constructor
  }

  public Credential(String id, String userId, String username, String password) {
    this.id = id;
    this.userId = userId;
    this.username = username;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
