package view;

import controller.ProfileController;
import utility.BankAccountData;
import utility.Utility;

import java.util.Scanner;

public class LoginView {

    Scanner sc = new Scanner(System.in);

    public void login() throws Exception {
        boolean isValidated;
        String input;
        input = getEmailOrUserName();
        String password = getPassword();
        isValidated = validation(input, password);
        if(isValidated) {
            ProfileController profile = new ProfileController();
            profile.getUserDetails(input);
            BankAccountData bankAccountData = new BankAccountData();
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
        Utility util = new Utility();
        if (util.loginValidation(email, password)) {
            System.out.println("Logged in successfully");
            return true;
        } else {
            System.out.println("Invalid login credentials. Try Again!");
            return false;
        }
    }
}