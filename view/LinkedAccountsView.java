package view;

import controller.LinkedAccountController;
import controller.ProfileController;
import model.LinkedAccount;

import java.util.ArrayList;
import java.util.Scanner;

public class LinkedAccountsView {
    Scanner sc = new Scanner(System.in);

    public void viewBankAccounts(ArrayList<LinkedAccount> accounts){
        if(accounts.size() == 0){
            System.out.println("\n-------- NO ACCOUNTS LINKED --------\n");
        }else {
            System.out.println("S.NO\t\tAccount Number\t\t\tBankName");
            for (int i = 0; i < accounts.size(); i++) {
                System.out.println(i + 1 + ".  \t\t" + accounts.get(i).getAccountNumber() + "\t\t\t\t" + accounts.get(i).getBankName());
            }
        }
    }

    public void accountOperations(ProfileController profile) {
        boolean isValidOption = false;
        while(!isValidOption) {
            System.out.println("1. Link bank account\n2. Remove bank account\n3. Back");
            System.out.print("Enter your choice: ");
            try {
                String option = sc.nextLine();
                int entryOption = Integer.parseInt(option);
                if(entryOption != 0 && entryOption <= enums.LinkedAccount.values().length){
                    enums.LinkedAccount entry = enums.LinkedAccount.values()[entryOption-1];
                    isValidOption = operations(entry, profile);
                }else{
                    System.out.println("Enter proper input.");
                }
            }catch(Exception error){
                System.out.println("Enter a valid option(Linked Account view)");
                sc.nextLine();
            }
        }
    }

    public boolean operations(enums.LinkedAccount option, ProfileController profile) throws Exception {
        switch(option) {
            case LINK_ACCOUNT:
                LinkedAccountController accountController = new LinkedAccountController();
                accountController.linkAccount(profile);
                return false;
            case REMOVE_ACCOUNT:
                viewBankAccounts(profile.getAccounts());
                if(profile.getAccounts().size() > 0){
                    accountController = new LinkedAccountController();
                    accountController.removeLinkedAccount(profile);
                }
                return false;
            case BACK:
                return true;
            default:
                System.out.println("Invalid option");
                return false;
        }
    }

    public int getAccountNumber(){
        System.out.print("Enter Account Number: ");
        return sc.nextInt();
    }

    public String getBankName(){
        sc.nextLine();
        System.out.print("Enter Bank Name: ");
        return sc.nextLine();
    }

    public void linkStatus(boolean status){
        if(status){
            System.out.println("Account Linked Successfully");
        }else{
            System.out.println("Account Already linked");
        }
    }

    public int getAccountToBeDeleted() {
        while(true){
            System.out.print("\nSelect the account to be deleted: ");
            try {
                String input = sc.nextLine();
                return Integer.parseInt(input);
            } catch (Exception error) {
                System.out.println("Invalid option. (Account to be deleted)");
            }
        }
    }

    public void removeStatus(boolean status) {
        if(status){
            System.out.println("Account removed Successfully");
        }else{
            System.out.println("Select from the linked accounts");
        }
    }
}
