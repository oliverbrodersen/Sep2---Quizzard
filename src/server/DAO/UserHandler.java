package server.DAO;

import shared.transferobjects.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserHandler implements UserData{
    private DatabaseConnection DBConn;
    private QuizData quizData;

    public UserHandler(DatabaseConnection DBConn) {
        this.DBConn = DBConn;
        quizData = new QuizHandler(this.DBConn);
    }

    @Override
    public void storeUser(UserClass user) {

    }

    @Override
    public UserClass retrieveUser(String email) throws SQLException, NullPointerException{
        List<Quiz> quizzes;
        ResultSet rs = DBConn.retrieveData("SELECT * FROM \"Quizzard_Database\".Users WHERE LOWER(Email) = LOWER('" + email + "');");
        while ( rs.next() ) {
            String email2 = rs.getString("Email");
            if (!(email.equalsIgnoreCase(email2)))
                break;
            String name = rs.getString("Name");
            String password = rs.getString("Password");
            char userClass = rs.getString("Userclass").charAt(0);
            switch (userClass)
            {
                case('H'):
                    quizzes = quizData.readQuizzes(email);
                    return new Host(email, name, password, (ArrayList<Quiz>)quizzes);
                case('P'):
                    break;
                case('M'):
                    return new Moderator(email, name, password);
            }
        }
        return null;
    }
}
