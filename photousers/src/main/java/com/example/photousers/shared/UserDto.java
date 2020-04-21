package com.example.photousers.shared;


import com.example.photousers.ui.models.AlbumResponseModel;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class UserDto implements Serializable {

  private static final long serialVersionUID = -9271361946L;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String userId;
  private String encriptedPassword;
  private List<AlbumResponseModel> albums;

  public List<AlbumResponseModel> getAlbums() {
    return albums;
  }



  public void setAlbums(List<AlbumResponseModel> albums) {
    this.albums = albums;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getEncriptedPassword() {
    return encriptedPassword;
  }

  public void setEncriptedPassword(String encriptedPassword) {
    this.encriptedPassword = encriptedPassword;
  }
}
