package shared.transferobjects;

import client.networking.Client;
import shared.networking.ClientCallback;

import java.io.Serializable;

public interface UserClass extends Serializable
{

  String getUsername();

  String getPassword();

  String getEmail();

  UserID getUserID();

  ClientCallback getClient();

  void setUsername(String name);

  void setPassword(String password);

  void setEmail(String email);

  void setClient(Client client);
}
