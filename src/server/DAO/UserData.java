package server.DAO;

import shared.transferobjects.UserClass;

import java.sql.SQLException;

public interface UserData {
    void storeUser(UserClass user);
    UserClass retrieveUser(String email, String password) throws SQLException;
}
