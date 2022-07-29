package controller;

import model.Incoming;
import model.LinkedAccount;
import utility.Helper;
import utility.IncomingData;
import view.*;

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

    public void editIncoming(ArrayList<Incoming> incomingList, ProfileController profile) throws Exception {
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
                int index;
                Helper helper = new Helper();
                do index = editIncomingView.getAccount(); while(!helper.checkValidRecord(index, incomingList.size()));
                LinkedAccount account = new LinkedAccount();
                account.setAccountNumber(profile.getAccounts().get(index-1).getAccountNumber());
                account.setBankName(profile.getAccounts().get(index-1).getBankName());
                incoming.setAccount(account);
                incoming.setAmount(amount);
                incoming.setIncomingId(incomingList.get(recordToBeEdited-1).getIncomingId());
                incomingData.editIncomingRecord(incoming);
                editIncomingView.displayStatus();
                break;
            }else{
                editIncomingView.displayError();
            }
        }
    }

    public void deleteIncoming(ArrayList<Incoming> incomingList) throws Exception {
        Helper helper = new Helper();
        DeleteIncomingView deleteIncomingView = new DeleteIncomingView();
        while(true){
            incomingHistoryView.displayIncoming(incomingList);
            int recordToBeDeleted = deleteIncomingView.getRecord();
            if(helper.checkValidRecord(recordToBeDeleted, incomingList.size())){
                incomingData.deleteIncomingRecord(incomingList.get(recordToBeDeleted-1).getIncomingId());
                deleteIncomingView.deleteStatus();
                break;
            }else{
                deleteIncomingView.displayError();
            }
        }
    }
}