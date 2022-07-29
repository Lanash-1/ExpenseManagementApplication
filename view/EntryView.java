package view;

import enums.Entry;

import java.util.Scanner;

public class EntryView {

    Scanner sc = new Scanner(System.in);
    public void entryView() {
        while (true) {
            System.out.print("1.Login\n2.Signup\n3.Exit\nEnter your choice: ");
            try {
                String option = sc.nextLine();
                int entryOption = Integer.parseInt(option);
                if(entryOption != 0 && entryOption <= Entry.values().length){
                    Entry entry = Entry.values()[entryOption-1];
                    if(entryOperations(entry)){
                        break;
                    }
                }else{
                    System.out.println("Enter proper input.");
                }
            } catch (Exception error) {
                System.out.println("Enter a valid option. Try Again!");
            }
        }
    }

    public boolean entryOperations(Entry entry) throws Exception {
        switch (entry) {
            case LOGIN:
                LoginView loginView = new LoginView();
                loginView.login();
                return false;
            case SIGNUP:
                SignupView signupView = new SignupView();
                signupView.signup();
                return false;
            case EXIT:
                System.out.println("Closed");
                return true;
            default:
                System.out.println("Enter valid option. Try Again!");
                return false;
        }
    }
}