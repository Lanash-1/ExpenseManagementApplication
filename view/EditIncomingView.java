package view;

import java.util.Scanner;

public class EditIncomingView {
    Scanner sc = new Scanner(System.in);

    public int getRecord() {
        while(true){
            System.out.print("Select a record to edit");
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
                return sc.nextDouble();
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
}
