package com.splitscale.shield.userinfo;

import java.util.Date;

public class UserInfo {
  private String id;
  private Date created;
  private Date edited;
  private String firstName;
  private String lastName;
  private String photoUrl;
  private String email;

  public UserInfo() {
    // default constructor
  }

  public UserInfo(String id, Date created, Date edited, String firstName, String lastName, String photoUrl,
      String email) {
    this.id = id;
    this.created = created;
    this.edited = edited;
    this.firstName = firstName;
    this.lastName = lastName;
    this.photoUrl = photoUrl;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getEdited() {
    return edited;
  }

  public void setEdited(Date edited) {
    this.edited = edited;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
