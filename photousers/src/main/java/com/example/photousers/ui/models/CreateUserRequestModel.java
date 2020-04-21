package com.example.photousers.ui.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {
  @NotNull(message = "usted debe llenar este campoo")
  @Size(min=2, message = "debe tener mas de 2 caracteres")
  private String firstName;

  @NotNull(message = "usted debe llenar este campoo")
  @Size(min=2, message = "debe tener mas de 2 caracteres")
  private String lastName;

  @NotNull(message = "usted debe llenar este campoo")
  @Size(min=2, message = "debe tener mas de 2 caracteres")
  private String password;

  @NotNull(message = "usted debe llenar este campoo")
  @Email
  private String email;

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
}
