package view;
import controller.ExpenseController;
import controller.IncomingController;
import controller.LinkedAccountController;
import controller.ProfileController;
import enums.Dashboard;

import java.util.Scanner;

public class DashboardView {

    Scanner sc = new Scanner(System.in);

    IncomingController incomingController;
    ExpenseController expenseController;

    public void viewDashboard(ProfileController profile){
        while (true) {
            System.out.print("\n1.Profile\n2.Linked Accounts\n3.Add Expense\n4.Add Incoming\n5.Expense History\n6.Incoming History\n7.Sign out\nEnter your choice: ");
            try {
                String option = sc.nextLine();
                int entryOption = Integer.parseInt(option);
                if(entryOption != 0 && entryOption <= Dashboard.values().length){
                    Dashboard entry = Dashboard.values()[entryOption-1];
                    if(dashboardOperation(entry, profile)){
                        break;
                    }
                }else{
                    System.out.println("Enter proper input.");
                }
            } catch (Exception error) {
                System.out.println("Enter a valid option. Try Again!(view dashboard)");
            }
        }
    }

    private boolean dashboardOperation(Dashboard entryOption, ProfileController profile) throws Exception {
        switch (entryOption){
            case PROFILE:
                ProfileView profileView = new ProfileView();
                profileView.getProfileDetails(profile);
                return false;
            case LINKED_ACCOUNTS:
                LinkedAccountController accountController = new LinkedAccountController();
                accountController.getAccountDetails(profile);
                return false;
            case ADD_EXPENSE:
                expenseController = new ExpenseController();
                expenseController.addExpense(profile);
                return false;
            case ADD_INCOMING:
                incomingController = new IncomingController();
                incomingController.addIncoming(profile);
                return false;
            case EXPENSE_HISTORY:
                expenseController = new ExpenseController();
                expenseController.viewExpenseHistory(profile);
                return false;
            case INCOMING_HISTORY:
                incomingController = new IncomingController();
                incomingController.viewIncomingHistory(profile);
                return false;
            case SIGN_OUT:
                return true;
            default:
                System.out.println("Enter a valid option.\n");
        }
        return false;
    }
}