package utility;

import interfaces.BankAccountServices;
import model.LinkedAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BankAccountData implements BankAccountServices {

    private static final String URL = "jdbc:mysql://localhost:3306/DemoBase";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "password";

    String query;
    Connection con;
    ResultSet rs;
    Statement st;

    public BankAccountData() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public ArrayList<LinkedAccount> getBankDetails(String userId) throws Exception{
        query = "select AccountNumber, BankName from BankAccount where UserID = '"+userId+"'";
        st = con.createStatement();
        rs = st.executeQuery(query);
        ArrayList<LinkedAccount> accounts = new ArrayList<>();
        while(rs.next()){
            LinkedAccount account = new LinkedAccount();
            account.setAccountNumber(rs.getInt(1));
            account.setBankName(rs.getString(2));
            accounts.add(account);
        }
        return accounts;
    }

    public void linkAccount(String userId, LinkedAccount accounts) throws Exception {
        query = "insert into BankAccount values('"+userId+"',"+accounts.getAccountNumber()+",'"+accounts.getBankName()+"')";
        st = con.createStatement();
        st.executeUpdate(query);
    }

    public void removeAccount(LinkedAccount accounts) throws Exception{
        query = "delete from BankAccount where AccountNumber = "+accounts.getAccountNumber();
        st = con.createStatement();
        st.executeUpdate(query);
        query = "delete from Incoming where AccountNumber = "+accounts.getAccountNumber();
        st = con.createStatement();
        st.executeUpdate(query);
        query = "delete from Expense where AccountNumber = "+accounts.getAccountNumber();
        st = con.createStatement();
        st.executeUpdate(query);
    }
}
