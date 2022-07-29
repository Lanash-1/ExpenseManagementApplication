package utility;

import model.Expense;
import model.Incoming;
import model.LinkedAccount;

import java.sql.*;
import java.util.ArrayList;


public class Utility{

    private static final String URL = "jdbc:mysql://localhost:3306/DemoBase";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "password";

    String query;
    Connection con;
    ResultSet rs;
    Statement st;

    public Utility() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public boolean loginValidation(String input, String password) throws Exception{
        query = "select count(*) from UserData where EmailID = '"+input+"' and Password = '"+password+"' or UserID = '"+input+"' and Password = '"+password+"';";
        st = con.createStatement();
        rs = st.executeQuery(query);
        while(rs.next()) {
            return rs.getInt(1) == 1;
        }
        return false;
    }

    public boolean checkUnique(String input, String field) throws Exception {
        query = "select count("+field+"), "+field+" from UserData where "+field+" = '" + input + "'";
        st = con.createStatement();
        rs = st.executeQuery(query);
        while(rs.next()){
            return rs.getInt(1) == 0;
        }
        return true;
    }

    public boolean linkedAccountExists(int accountNumber) throws Exception{
        query = "select count(AccountNumber), AccountNumber from BankAccount where AccountNumber = "+ accountNumber;
        st = con.createStatement();
        rs = st.executeQuery(query);
        while(rs.next()){
            if(rs.getInt(1) == 0){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Incoming> filterIncoming(String userName, int accountNumber) throws Exception{
        query = "select * from Incoming where UserID = '"+ userName +"' and AccountNumber = "+accountNumber;
        st = con.createStatement();
        rs = st.executeQuery(query);
        ArrayList<Incoming> incomingHistory = new ArrayList<>();
        while(rs.next()){
            Incoming incoming = new Incoming();
            LinkedAccount account = new LinkedAccount();
            account.setAccountNumber(rs.getInt(3));
            incoming.setAccount(account);
            incoming.setAmount(rs.getDouble(2));
            incomingHistory.add(incoming);
        }
        return incomingHistory;
    }

    public ArrayList<Expense> filterExpense(String userName, int accountNumber) throws Exception {
        query = "select * from Expense where UserID = '"+ userName +"' and AccountNumber = "+accountNumber;
        st = con.createStatement();
        rs = st.executeQuery(query);
        ArrayList<Expense> expenseHistory = new ArrayList<>();
        while(rs.next()){
            Expense expense = new Expense();
            LinkedAccount account = new LinkedAccount();
            account.setAccountNumber(rs.getInt(4));
            expense.setAccount(account);
            expense.setAmount(rs.getDouble(3));
            expense.setCategory(rs.getString(2));
            expenseHistory.add(expense);
        }
        return expenseHistory;
    }

    public ArrayList<Expense> filterExpense(String userName, String category) throws Exception {
        query = "select * from Expense where UserID = '"+ userName +"' and Category = '"+category+"'";
        st = con.createStatement();
        rs = st.executeQuery(query);
        ArrayList<Expense> expenseHistory = new ArrayList<>();
        while(rs.next()){
            Expense expense = new Expense();
            LinkedAccount account = new LinkedAccount();
            account.setAccountNumber(rs.getInt(4));
            expense.setAccount(account);
            expense.setAmount(rs.getDouble(3));
            expense.setCategory(rs.getString(2));
            expenseHistory.add(expense);
        }
        return expenseHistory;
    }

    public ArrayList<String> getCategoryList(String userName) throws Exception {
        query = "select distinct Category from Expense where UserID = '"+userName+"'";
        st = con.createStatement();
        rs = st.executeQuery(query);
        ArrayList<String> categoryList = new ArrayList<>();
        while(rs.next()){
            categoryList.add(rs.getString(1));
        }
        return categoryList;
    }


}