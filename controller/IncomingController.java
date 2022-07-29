package controller;

import model.Incoming;
import model.LinkedAccount;
import utility.IncomingData;
import utility.Utility;
import view.AddIncomingView;
import view.EditIncomingView;
import view.IncomingHistoryView;
import view.LinkedAccountsView;

import java.util.ArrayList;

public class IncomingController {

    private IncomingHistoryView incomingHistoryView;
    private AddIncomingView addIncomingView;
    private EditIncomingView editIncomingView;

    private Incoming incoming;

    private IncomingData incomingData;


    public IncomingController() throws Exception {
        incomingHistoryView = new IncomingHistoryView();
        addIncomingView = new AddIncomingView();
        incoming = new Incoming();
        editIncomingView = new EditIncomingView();
        incomingData = new IncomingData();
    }

    public void addIncoming(ProfileController profile) throws Exception {
        if(profile.getAccounts().size() > 0){
            LinkedAccountsView linkedAccountsView = new LinkedAccountsView();
            linkedAccountsView.viewBankAccounts(profile.getAccounts());
            int index = addIncomingView.getAccount();
            if(profile.getAccounts().size() >= index){
                incoming.setAccount(profile.getAccounts().get(index-1));
                incoming.setAmount(addIncomingView.getAmount());
                incomingData.addIncoming(incoming, profile);
                addIncomingView.addIncomingStatus();
            }else{
                addIncomingView.noAccount();
            }
        }else{
            addIncomingView.showAddAccount();
        }
    }

    public void viewIncomingHistory(ProfileController profile) throws Exception {
        int choice = 0;
        while(choice != 5) {
            choice = incomingHistoryView.viewIncoming(incomingData.getIncomingHistory(profile.getUserName()), profile);
        }
    }

    public void editIncoming(ArrayList<Incoming> incomingList, ProfileController profile) {
        while(true){
            incomingHistoryView.displayIncoming(incomingList);
            int recordToBeEdited = editIncomingView.getRecord();
            if(incomingList.size() >= recordToBeEdited && recordToBeEdited != 0){
                double amount = 0;
                while(amount <= 0){
                    amount = editIncomingView.getAmount();
                }
                LinkedAccountsView linkedAccountsView = new LinkedAccountsView();
                linkedAccountsView.viewBankAccounts(profile.getAccounts());
                int index = 0;
                while(index == 0 && incomingList.size() < index){
                    index = editIncomingView.getAccount();
                }
                LinkedAccount account = new LinkedAccount();
                account.setAccountNumber(profile.getAccounts().get(index-1).getAccountNumber());
                account.setBankName(profile.getAccounts().get(index-1).getBankName());
                incoming.setAccount(account);
                incoming.setAmount(amount);

            }
        }

    }
}
