package view;

import java.util.Scanner;

public class AddIncomingView {

    Scanner sc = new Scanner(System.in);
    public int getAccount(){
        while(true){
            System.out.print("Select account: ");
            try {
                return sc.nextInt();
            } catch (Exception error) {
                System.out.println("Select a valid account. Try Again!(incoming view)");
                sc.nextLine();
            }
        }
    }

    public double getAmount() {
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
                System.out.println("Enter a valid amount. Try Again!(incoming view)");
                sc.nextLine();
            }
        }
    }

    public void addIncomingStatus() {
        System.out.println("\n---------- Incoming added to your profile ----------\n");
    }

    public void showAddAccount() {
        System.out.println("No accounts linked. Link a account to add Expense(incoming view)");
    }

    public void noAccount() {
        System.out.println("Select from available accounts");
    }
}
