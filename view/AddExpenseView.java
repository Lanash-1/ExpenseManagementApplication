package view;

import java.util.Scanner;

public class AddExpenseView {
    Scanner sc = new Scanner(System.in);

    public double getAmount(){
        while(true){
            System.out.print("Enter amount: ");
            try {
                double amount = sc.nextDouble();
                if(amount > 0){
                    return amount;
                }else{
                    System.out.println("Amount should be greater than zero.");
                    sc.nextLine();
                }
            } catch (Exception error) {
                System.out.println("Enter a valid amount. Try Again!");
                sc.nextLine();
            }
        }
    }

    public int getAccount(){
        while(true){
            System.out.print("Select account: ");
            try {
                return sc.nextInt();
            } catch (Exception error) {
                System.out.println("Select a valid account. Try Again!");
                sc.nextLine();
            }
        }
    }

    public void showAddAccount() {
        System.out.println("No accounts linked. Link a account to add Expense");
    }

    public String getCategory() {
        sc.nextLine();
        String[] category ={"GADGET", "ENTERTAINMENT", "FOOD", "TRAVEL", "HEALTH", "CUSTOM"};
        while(true) {
            for (int i=0; i<category.length ; i++) {
                System.out.println(i+1+". "+category[i]);
            }
            System.out.print("Enter your choice: ");
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                if(choice >0 && choice < 7){
                    if(choice == 6){
                        System.out.print("Enter your category: ");
                        return sc.nextLine();
                    }else{
                        return category[choice-1];
                    }
                }else{
                    System.out.println("Select from the given category options");
                }
            } catch (Exception error) {
                sc.nextLine();
                System.out.println("Enter valid category option.");
            }
        }
    }

    public void addExpenseStatus(){
        System.out.println("\n---------- Expense added to your profile ----------\n");
    }

    public void noAccount() {
        System.out.println("Select from available accounts");
    }
}
