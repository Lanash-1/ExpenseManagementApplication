package view;

import java.util.Scanner;

public class DeleteExpenseView {
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

    public void deleteStatus() {
        System.out.println("Record deleted successfully");
    }

    public void displayError() {
        System.out.println("select from available record.");
    }
}
