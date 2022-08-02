package utility;

import java.util.Scanner;

public class Helper {

    Scanner sc = new Scanner(System.in);

    public boolean checkValidRecord(int record, int size) {
        return size >= record && record != 0;
    }

    public boolean fieldValidation(String input){
        if(input.equals("")){
            System.out.println("Field should not be empty");
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password, String rePassword){
        return rePassword.equals(password);
    }

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


    public String getCategory() {
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
                        String input;
                        while(true) {
                            System.out.print("Enter your category: ");
                            input = sc.nextLine();
                            if(fieldValidation(input)){
                                return input;
                            }
                        }
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

}
