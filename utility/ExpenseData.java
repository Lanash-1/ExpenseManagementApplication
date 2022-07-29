package utility;

import controller.ProfileController;
import interfaces.ExpenseServices;
import model.Expense;
import model.LinkedAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ExpenseData implements ExpenseServices {

    private static final String URL = "jdbc:mysql://localhost:3306/DemoBase";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "password";

    String query;
    Connection con;
    ResultSet rs;
    Statement st;

    public ExpenseData() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public void addExpense(Expense expense, ProfileController profile) throws Exception {
        query = "insert into Expense(UserID, Category, Amount, AccountNumber) values('"+profile.getUserName()+"','"+expense.getCategory()+"',"+expense.getAmount()+","+expense.getAccount().getAccountNumber()+")";
        st = con.createStatement();
        st.executeUpdate(query);
    }
    public ArrayList<Expense> getExpenseHistory(String userId) throws Exception {
        query = "select * from Expense where UserID = '"+userId+"'";
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
            expense.setExpenseId(rs.getInt(5));
            expenseHistory.add(expense);
        }
        return expenseHistory;
    }

    public double getTotalExpense(String userId) throws Exception {
        query = "select sum(Amount) from Expense where UserID = '"+userId+"'";
        st = con.createStatement();
        rs = st.executeQuery(query);
        while(rs.next()){
            return rs.getDouble(1);
        }
        return 0;
    }

    public void deleteExpenseRecord(int expenseId) throws Exception {
        query = "delete from Expense where ExpenseID = "+expenseId;
        st = con.createStatement();
        st.executeUpdate(query);
    }

    @Override
    public void editExpenseRecord(Expense expense) throws Exception {
        query = "update Expense set Amount = "+expense.getAmount()+", AccountNumber = "+expense.getAccount().getAccountNumber()+", Category = '"+expense.getCategory()+"' where ExpenseID = "+expense.getExpenseId();
        System.out.println(query);
        st = con.createStatement();
        st.executeUpdate(query);
    }
}
