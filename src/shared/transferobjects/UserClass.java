package shared.transferobjects;

import java.io.Serializable;

public interface UserClass extends Serializable
{

  String getUsername();

  String getPassword();

  String getEmail();

  UserID getUserID();

  void setUsername(String name);

  void setPassword(String password);

  void setEmail(String email);


}
