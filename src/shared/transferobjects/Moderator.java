package shared.transferobjects;

import client.networking.Client;
import shared.networking.ClientCallback;

import java.io.Serializable;

public class Moderator implements UserClass
{

  private String name, password, email;
  private UserID userID;
  private ClientCallback client;

  public Moderator(String name, String password, String email) {
    this.name = name;
    this.password = password;
    this.email = email;
    userID = UserID.MODERATOR; //Gives the user class for login validation
  }


  // Getters
  @Override public String getUsername() {
    return name;
  }

  @Override public String getPassword() {
    return password;
  }

  @Override public String getEmail() {
    return email;
  }

  @Override public UserID getUserID() {
    return userID;
  }

  @Override public ClientCallback getClient()
  {
    return client;
  }

  // Setters
  @Override public void setUsername(String name) {
    this.name = name;
  }

  @Override public void setPassword(String password) {
    this.password = password;
  }

  @Override public void setEmail(String email) {
    this.email = email;
  }

  @Override public void setClient(Client client)
  {
    this.client = (ClientCallback)client;
  }

}
