package utility;

import controller.ProfileController;
import interfaces.IncomingServices;
import model.Incoming;
import model.LinkedAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class IncomingData implements IncomingServices {

    private static final String URL = "jdbc:mysql://localhost:3306/DemoBase";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "password";

    String query;
    Connection con;
    ResultSet rs;
    Statement st;
    public IncomingData() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public void addIncoming(Incoming incoming, ProfileController profile) throws Exception {
        query = "insert into Incoming(UserId, Amount, AccountNumber) values('"+profile.getUserName()+"',"+incoming.getAmount()+","+incoming.getAccount().getAccountNumber()+")";
        st = con.createStatement();
        st.executeUpdate(query);
    }

    public ArrayList<Incoming> getIncomingHistory(String userName) throws Exception{
        query = "select * from Incoming where UserID = '"+userName+"'";
        st = con.createStatement();
        rs = st.executeQuery(query);
        ArrayList<Incoming> incomingHistory = new ArrayList<>();
        while(rs.next()){
            Incoming incoming = new Incoming();
            LinkedAccount account = new LinkedAccount();
            account.setAccountNumber(rs.getInt(3));
            incoming.setAccount(account);
            incoming.setAmount(rs.getDouble(2));
            incoming.setIncomingId(rs.getInt(4));
            incomingHistory.add(incoming);
        }
        return incomingHistory;
    }

    public double getTotalIncoming(String userId) throws Exception {
        query = "select sum(Amount) from Incoming where UserID = '"+userId+"'";
        st = con.createStatement();
        rs = st.executeQuery(query);
        while(rs.next()){
            return rs.getDouble(1);
        }
        return 0;
    }

    public void deleteIncomingRecord(int incomingId) throws Exception{
        query = "delete from Incoming where IncomingID = "+incomingId;
        st = con.createStatement();
        st.executeUpdate(query);
    }


}
