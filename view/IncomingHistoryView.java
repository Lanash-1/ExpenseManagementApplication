package view;

import controller.IncomingController;
import controller.ProfileController;
import interfaces.PrintService;
import model.Incoming;
import utility.Helper;
import utility.IncomingData;
import utility.PrintData;
import utility.Utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IncomingHistoryView {
    Scanner sc = new Scanner(System.in);

    private ArrayList<Incoming> incoming;

    IncomingData incomingData;

    public IncomingHistoryView() throws Exception {
        incomingData = new IncomingData();
    }

    public void displayIncoming(ArrayList<Incoming> incomingList){
        if(incomingList.size() == 0){
            System.out.println("No incoming to show.");
        }else{
            System.out.println("\nS.No\t\tAccount Number\t\t\tAmount");
            for (int i = 0; i < incomingList.size(); i++) {
                System.out.println(i + 1 + ".\t\t\t" + incomingList.get(i).getAccount().getAccountNumber() + "\t\t\t\t" + incomingList.get(i).getAmount());
            }
        }
    }

    public int viewIncoming(ArrayList<Incoming> incomingList, ProfileController profile) {
        incoming = incomingList;
        if(incomingList.size() == 0){
            System.out.println("\nNo incoming to show");
        }else{
            while(true) {
                displayIncoming(incomingList);
                System.out.println("\n1. Print Incoming History\n2. Filter by Account\n3. Edit Incoming\n4. Delete Incoming\n5. Back");
                System.out.print("Enter your choice: ");
                try{
                    String input = sc.nextLine();
                    int option = Integer.parseInt(input);
                    if(option != 0 && option <= enums.Incoming.values().length){
                        enums.Incoming entry = enums.Incoming.values()[option-1];
                        return incomingHistoryOperations(entry, profile);
                    }else{
                        System.out.println("Enter proper input.");
                    }
                } catch (Exception error){
                    System.out.println("Enter a valid option.(view incoming history)");
                }
            }
        }
        return 5;
    }

    public int incomingHistoryOperations(enums.Incoming choice, ProfileController profile) throws Exception {
        switch(choice) {
            case PRINT:
                PrintData print = new PrintData();
                print.printIncomingHistory(incoming);
                return 0;
            case FILTER:
                LinkedAccountsView linkedAccounts = new LinkedAccountsView();
                while (true) {
                    linkedAccounts.viewBankAccounts(profile.getAccounts());
                    System.out.print("Select account to filter: ");
                    try {
                        String input = sc.nextLine();
                        int index = Integer.parseInt(input);
                        if (profile.getAccounts().size() >= index && index != 0) {
                            Utility util = new Utility();
                            return viewIncoming(util.filterIncoming(profile.getUserName(), profile.getAccounts().get(index - 1).getAccountNumber()), profile);
                        }
                    } catch (Exception error) {
                        System.out.println("Select valid account.");
                    }
                }
            case EDIT:
                IncomingController incomingController = new IncomingController();
                incomingController.editIncoming(incoming, profile);
                return 3;
            case DELETE:
                incoming = incomingData.getIncomingHistory(profile.getUserName());
                int record;
                Helper helper = new Helper();
                while (true) {
                    displayIncoming(incoming);
                    record = getRecord();
                    if (helper.checkValidRecord(record, incoming.size())) {
                        break;
                    } else {
                        System.out.println("Select a valid record");
                    }
                }
                incomingData.deleteIncomingRecord(incoming.get(record - 1).getIncomingId());
                System.out.println("\nRecord deleted successfully");
                incoming = incomingData.getIncomingHistory(profile.getUserName());
                if (incoming.size() == 0) {
                    displayIncoming(incoming);
                    return 6;
                }
                return viewIncoming(incomingData.getIncomingHistory(profile.getUserName()), profile);
            case BACK:
                return 5;
            default:
                System.out.println("Select only valid options. (incomingView)");
        }
        return 0;
    }

    public int getRecord(){
        System.out.print("Select a record to delete: ");
        try{
            String input = sc.nextLine();
            return Integer.parseInt(input);
        }catch (Exception error){
            System.out.println("Enter a valid option.");
        }
        return 0;
    }
}
