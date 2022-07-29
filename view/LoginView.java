package view;

import controller.ProfileController;
import utility.BankAccountData;
import utility.Utility;

import java.util.Scanner;

public class LoginView {

    Scanner sc = new Scanner(System.in);
    Utility util;
    BankAccountData bankAccountData;


    public LoginView() throws Exception {
        util = new Utility();
        bankAccountData = new BankAccountData();
    }

    public void login() throws Exception {
        boolean isValidated;
        String input;
        input = getEmailOrUserName();
        String password = getPassword();
        isValidated = validation(input, password);
        if(isValidated) {
            ProfileController profile = new ProfileController();
            profile.getUserDetails(input);
            profile.setAccounts(bankAccountData.getBankDetails(profile.getUserName()));
            DashboardView dashboard = new DashboardView();
            dashboard.viewDashboard(profile);
        }
    }

    public String getEmailOrUserName() {
        System.out.print("Enter emailID / UserName: ");
        return sc.nextLine();
    }

    public String getPassword(){
        System.out.print("Enter password: ");
        return sc.nextLine();
    }

    public boolean validation(String email, String password) throws Exception {
        if (util.loginValidation(email, password)) {
            System.out.println("Logged in successfully");
            return true;
        } else {
            System.out.println("Invalid login credentials. Try Again!");
            return false;
        }
    }
}