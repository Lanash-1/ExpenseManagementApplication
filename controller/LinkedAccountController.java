package controller;

import model.LinkedAccount;
import utility.BankAccountData;
import utility.Utility;
import view.LinkedAccountsView;

public class LinkedAccountController {

    private final LinkedAccount account;
    private final LinkedAccountsView accountView;

    Utility util = new Utility();
    BankAccountData bankAccountData = new BankAccountData();

    public LinkedAccountController() throws Exception {
        account = new LinkedAccount();
        accountView = new LinkedAccountsView();
    }

    public void getAccountDetails(ProfileController profile) throws Exception {
        profile.setAccounts(bankAccountData.getBankDetails(profile.getUserName()));
        accountView.viewBankAccounts(profile.getAccounts());
        accountView.accountOperations(profile);
    }

    public void removeLinkedAccount(ProfileController profile) throws Exception {

        int index = accountView.getAccountToBeDeleted();
        if(profile.getAccounts().size() >= index-1){
            bankAccountData.removeAccount(profile.getAccounts().get(index-1));
            accountView.removeStatus(true);
            profile.setAccounts(bankAccountData.getBankDetails(profile.getUserName()));
        }else{
            accountView.removeStatus(false);
        }
        accountView.viewBankAccounts(profile.getAccounts());
    }

    public void linkAccount(ProfileController profile) throws Exception {
        account.setAccountNumber(accountView.getAccountNumber());
        if(util.linkedAccountExists(account.getAccountNumber())){
            account.setBankName(accountView.getBankName());
            bankAccountData.linkAccount(profile.getUserName(), account);
            accountView.linkStatus(true);
            profile.setAccounts(bankAccountData.getBankDetails(profile.getUserName()));
        }else{
            accountView.linkStatus(false);
        }
        accountView.viewBankAccounts(profile.getAccounts());
    }
}
