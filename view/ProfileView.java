package view;
import controller.ProfileController;

import java.util.Scanner;

public class ProfileView {
    Scanner sc = new Scanner(System.in);
    public void getProfileDetails(ProfileController profileController) throws Exception {
        while(true) {
            printProfileDetails(profileController);
            System.out.println("Press 0 to go back to dashboard");
            try {
                String input = sc.nextLine();
                int option = Integer.parseInt(input);
                if (option != 0) {
                    System.out.println("Enter a valid option");
                } else {
                    break;
                }
            } catch (Exception error) {
                System.out.println("Enter a valid option");
            }
        }
    }

    public void printProfileDetails(ProfileController profileController) throws Exception {
        System.out.println("\n-----------------  PROFILE  -----------------\n");
        System.out.println("UserName: " + profileController.getUserName());
        System.out.println("FirstName: "+profileController.getFirstName()+"\t||\tLastName: "+profileController.getLastName());
        System.out.println("Email ID: "+ profileController.getEmailId());
        System.out.println("Password: "+ profileController.getPassword());
        double incoming, expense;
        expense = profileController.getTotalExpense(profileController.getUserName());
        incoming = profileController.getTotalIncoming(profileController.getUserName());
        System.out.println("Total Incoming: "+incoming+"\t\t\tTotal Expense: "+expense);
        if(incoming-expense > 0){
            System.out.println("Your expenses are lower than your incomings by Rs." + (incoming-expense));
        }else if(incoming-expense < 0){
            System.out.println("Your expenses are higher than your incomings by Rs. " + (expense-incoming));
        }else{
            System.out.println("Your expenses and incomings are balanced");
        }
        System.out.println("\n---------------------------------------------\n");
    }
}