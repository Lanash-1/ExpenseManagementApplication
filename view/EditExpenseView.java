package view;

import java.util.Scanner;

public class EditExpenseView {

    Scanner sc = new Scanner(System.in);
    public int getRecord() {
        while(true){
            System.out.print("Select a record to edit: ");
            try{
                String option = sc.nextLine();
                return Integer.parseInt(option);
            }catch(Exception error){
                System.out.println("Select a valid record. (getRecord)");
            }
        }
    }

    public double getAmount() {
        while(true){
            System.out.print("Enter new amount: ");
            try{
                double amount = sc.nextDouble();
                sc.nextLine();
                return amount;
            } catch(Exception error){
                System.out.println("Enter valid amount");
                sc.nextLine();
            }
        }
    }


    public int getAccount() {
        while(true){
            System.out.print("Select Account: ");
            try{
                String option = sc.nextLine();
                return Integer.parseInt(option);
            }catch (Exception error){
                System.out.println("Enter a valid option.");
            }
        }
    }

    public void displayStatus() {
        System.out.println("Record edited successfully");
    }
}
