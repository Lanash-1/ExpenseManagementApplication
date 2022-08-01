package view;

import controller.ProfileController;
import utility.BankAccountData;
import utility.Helper;
import utility.UserData;
import utility.Utility;

import java.util.Scanner;

public class SignupView {
    Scanner sc = new Scanner(System.in);

    public void signup() throws Exception {
        Helper helper = new Helper();
        Utility util = new Utility();
        String email;
        String password;
        String firstName;
        String lastName;
        String userName;

        while (true) {
            email = getEmailId();
            if (helper.fieldValidation(email)) {
                if(util.checkUnique(email, "EmailID")) {
                    break;
                }else{
                    System.out.println("EmailID Already Exists");
                    return;
                }
            }
        }

        do {
            password = getPassword();
        } while (!helper.fieldValidation(password));

        verifyPassword(password);

        while (true) {
            userName = getUserId();
            if (helper.fieldValidation(userName)) {
                if(util.checkUnique(userName, "UserID")) {
                    break;
                }else{
                    System.out.println("UserID taken already! Try others!");
                }
            }
        }

        do {
            firstName = getFirstName();
        } while (!helper.fieldValidation(firstName));

        do {
            lastName = getLastName();
        } while (!helper.fieldValidation(lastName));

        UserData userData = new UserData();

        userData.createAccount(userName, firstName, lastName, email, password);
        System.out.println("----------------------------------------\nAccount created Successfully.\n----------------------------------------");
        ProfileController profile = new ProfileController();
        profile.getUserDetails(email);
        BankAccountData bankAccountData = new BankAccountData();
        profile.setAccounts(bankAccountData.getBankDetails(profile.getUserName()));
        DashboardView dashboard = new DashboardView();
        dashboard.viewDashboard(profile);
    }

    private String getUserId() {
        System.out.print("Create your userId: ");
        return sc.nextLine();
    }

    private String getLastName() {
        System.out.print("Enter Last Name: ");
        return sc.nextLine();
    }

    private String getFirstName() {
        System.out.print("Enter First Name: ");
        return sc.nextLine();
    }

    private String getRePassword() {
        System.out.print("Re-enter password: ");
        return sc.nextLine();
    }

    public String getEmailId() {
        System.out.print("Enter email id: ");
        return sc.nextLine();
    }

    public String getPassword(){
        System.out.print("Enter password: ");
        return sc.nextLine();
    }

    public void verifyPassword(String password){
        Helper helper = new Helper();
        while(true) {
            String rePassword = getRePassword();
            if(helper.validatePassword(password, rePassword)){
                break;
            }else{
                System.out.println("Password not matching, Enter again");
            }
        }
    }
}