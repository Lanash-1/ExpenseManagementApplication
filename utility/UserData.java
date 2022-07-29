package utility;

import interfaces.UserDataServices;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserData implements UserDataServices {

    private static final String URL = "jdbc:mysql://localhost:3306/DemoBase";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "password";

    String query;
    Connection con;
    ResultSet rs;
    Statement st;

    public UserData() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public User getProfileDetail(String input) throws Exception {
        query = "select * from UserData where EmailID = '"+input+"' or UserID = '"+input+"'";
        st = con.createStatement();
        rs = st.executeQuery(query);
        User user = new User();
        while(rs.next()){
            user.setUserName(rs.getString(1));
            user.setFirstName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setEmailId(rs.getString(4));
            user.setPassword(rs.getString(5));
        }
        return user;
    }

    public void createAccount(String userName, String firstName, String lastName, String emailId, String password) throws Exception {
        query = "insert into UserData(UserID, FirstName, LastName, EmailID, Password) values('"+userName+"','"+firstName+"','"+lastName+"','"+emailId+"','"+password+"')";
        st = con.createStatement();
        st.executeUpdate(query);
    }
}
