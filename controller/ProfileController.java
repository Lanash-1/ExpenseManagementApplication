package controller;
import model.LinkedAccount;
import model.User;
import utility.ExpenseData;
import utility.IncomingData;
import utility.UserData;

import java.util.ArrayList;

public class ProfileController {
    private User user;

    public ProfileController(){
        user = new User();
    }

    public String getUserName(){
        return user.getUserName();
    }


    public String getFirstName(){
        return user.getFirstName();
    }


    public String getLastName(){
        return user.getLastName();
    }


    public String getEmailId(){
        return user.getEmailId();
    }


    public String getPassword(){
        return user.getPassword();
    }

    public void setPassword(String password){
        user.setPassword(password);
    }

    public ArrayList<LinkedAccount> getAccounts(){
        return user.getAccounts();
    }

    public void setAccounts(ArrayList<LinkedAccount> accounts){
        user.setAccounts(accounts);
    }

    public void getUserDetails(String input) throws Exception {
        UserData userData = new UserData();
        user = userData.getProfileDetail(input);
    }

    public double getTotalIncoming(String userId) throws Exception{
        IncomingData incomingData = new IncomingData();
        return incomingData.getTotalIncoming(userId);
    }


    public double getTotalExpense(String userId) throws Exception {
        ExpenseData expenseData = new ExpenseData();
        return expenseData.getTotalExpense(userId);
    }
}