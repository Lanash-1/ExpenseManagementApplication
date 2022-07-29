package view;

import controller.ExpenseController;
import controller.ProfileController;
import model.Expense;
import utility.ExpenseData;
import utility.PrintData;
import utility.Utility;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseHistoryView{

    Scanner sc = new Scanner(System.in);

    private ArrayList<Expense> expense;
    ExpenseData expenseData;
    ExpenseController expenseController;

    public ExpenseHistoryView() throws Exception {
        expenseData = new ExpenseData();
    }

    public void displayExpense(ArrayList<Expense> expenseList){
        if(expenseList.size() == 0){
            System.out.println("No expense to show.");
        } else{
            System.out.println("\nS.No\t\tAccount Number\t\tAmount\t\tCategory");
            for(int i=0; i<expenseList.size(); i++){
                System.out.println(i+1+".\t\t\t"+expenseList.get(i).getAccount().getAccountNumber()+"\t\t\t"+expenseList.get(i).getAmount()+"\t\t\t"+expenseList.get(i).getCategory());
            }
        }
    }
    public int viewExpense(ArrayList<Expense> expenseList, ProfileController profile) {
        expense = expenseList;
        if(expenseList.size() == 0){
            System.out.println("\nNo Expense to show");
        }else{
            while(true) {
                displayExpense(expenseList);
                System.out.println("\n1. Print Expense History\n2. Filter by account\n3. Filter by category\n4. Edit Expense\n5. Delete Expense\n6. Back");
                System.out.print("Enter your choice: ");
                try{
                    String input = sc.nextLine();
                    int option = Integer.parseInt(input);
                    if(option != 0 && option <= enums.Expense.values().length){
                        enums.Expense entry = enums.Expense.values()[option-1];
                        return expenseHistoryOperations(entry, profile);
                    }else{
                        System.out.println("Enter proper input.");
                    }
                } catch (Exception error){
                    System.out.println("Enter a valid option.(view expense history)");
                }
            }
        }
        return 6;
    }

    public int expenseHistoryOperations(enums.Expense choice, ProfileController profile) throws Exception {
        switch(choice){
            case PRINT:
                PrintData print = new PrintData();
                print.printExpenseHistory(expense);
                return 0;
            case FILTER_BY_ACCOUNT:
                LinkedAccountsView linkedAccounts = new LinkedAccountsView();
                while(true){
                    linkedAccounts.viewBankAccounts(profile.getAccounts());
                    System.out.print("Select account to filter: ");
                    try{
                        String input = sc.nextLine();
                        int index = Integer.parseInt(input);
                        if(profile.getAccounts().size() >= index && index != 0){
                            Utility util = new Utility();
                            return viewExpense(util.filterExpense(profile.getUserName(), profile.getAccounts().get(index-1).getAccountNumber()), profile);
                        }
                    }catch (Exception error){
                        System.out.println("Select valid account.");
                    }
                }
            case FILTER_BY_CATEGORY:
                while(true) {
                    Utility util = new Utility();
                    ArrayList<String> categoryList = util.getCategoryList(profile.getUserName());
                    for (int i = 0; i < categoryList.size(); i++) {
                        System.out.println(i + 1 + ". " + categoryList.get(i));
                    }
                    System.out.print("Select category to filter: ");
                    int category;
                    try {
                        category = sc.nextInt();
                        sc.nextLine();
                        if (categoryList.size() >= category && category != 0) {
                            return viewExpense(util.filterExpense(profile.getUserName(), categoryList.get(category - 1)), profile);
                        }else{
                            System.out.println("Select from listed category");
                        }
                    } catch (Exception error) {
                        sc.nextLine();
                        System.out.println("Enter valid option to filter.");
                    }
                }
            case EDIT:
                expenseController = new ExpenseController();
                expense = expenseData.getExpenseHistory(profile.getUserName());
                expenseController.editExpense(expense, profile);
                return 4;
            case DELETE:
                expenseController = new ExpenseController();
                expense = expenseData.getExpenseHistory(profile.getUserName());
                expenseController.deleteExpense(expense);
                expense = expenseData.getExpenseHistory(profile.getUserName());
                return viewExpense(expenseData.getExpenseHistory((profile.getUserName())), profile);
            case BACK:
                return 6;
            default:
                System.out.println("Select only valid options.(expense view)");
        }
        return 0;
    }

}