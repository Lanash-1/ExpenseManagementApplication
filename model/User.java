package model;

import java.util.ArrayList;

public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;

    private ArrayList<LinkedAccount> accounts;

    public ArrayList<LinkedAccount> getAccounts(){
        return accounts;
    }

    public void setAccounts(ArrayList<LinkedAccount> accounts){
        this.accounts = accounts;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmailId(){
        return emailId;
    }

    public void setEmailId(String emailId){
        this.emailId = emailId;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

}