package controller;

import model.Expense;
import model.LinkedAccount;
import utility.ExpenseData;
import utility.Helper;
import view.*;

import java.util.ArrayList;

public class ExpenseController {

    private final AddExpenseView addExpenseView;
    private final ExpenseHistoryView expenseHistoryView;
    private final Expense expense;
    private final ExpenseData expenseData;
    private final EditExpenseView editExpenseView;
    private final Helper helper;

    public ExpenseController() throws Exception {
        addExpenseView = new AddExpenseView();
        expenseHistoryView = new ExpenseHistoryView();
        expense = new Expense();
        expenseData = new ExpenseData();
        editExpenseView = new EditExpenseView();
        helper = new Helper();
    }

    public void viewExpenseHistory(ProfileController profile) throws Exception {
        int choice = 0;
        while(choice != 6){
            choice = expenseHistoryView.viewExpense(expenseData.getExpenseHistory(profile.getUserName()), profile);
        }
    }

    public void addExpense(ProfileController profile) throws Exception {
        if(profile.getAccounts().size() > 0){
            LinkedAccountsView linkedAccountsView = new LinkedAccountsView();
            linkedAccountsView.viewBankAccounts(profile.getAccounts());
            int index = helper.getAccount();
            if(profile.getAccounts().size() >= index && index != 0){
                expense.setAccount(profile.getAccounts().get(index-1));
                expense.setAmount(helper.getAmount());
                expense.setCategory(helper.getCategory());
                expenseData.addExpense(expense, profile);
                addExpenseView.addExpenseStatus();
            }else{
                addExpenseView.noAccount();
            }
        }else{
            addExpenseView.showAddAccount();
        }
    }

    public void editExpense(ArrayList<Expense> expenseList, ProfileController profile) throws Exception {
        while(true){
            expenseHistoryView.displayExpense(expenseList);
            int recordToBeEdited = helper.getRecord();
            if(expenseList.size() >= recordToBeEdited && recordToBeEdited != 0){
                double amount = helper.getAmount();
                LinkedAccountsView linkedAccountsView = new LinkedAccountsView();
                linkedAccountsView.viewBankAccounts(profile.getAccounts());
                int index;
                do index = helper.getAccount(); while(!helper.checkValidRecord(index, expenseList.size()));
                LinkedAccount account = new LinkedAccount();
                account.setAccountNumber(profile.getAccounts().get(index-1).getAccountNumber());
                account.setBankName(profile.getAccounts().get(index-1).getBankName());
                expense.setAccount(account);
                expense.setAmount(amount);
                expense.setCategory(helper.getCategory());
                expense.setExpenseId(expenseList.get(recordToBeEdited-1).getExpenseId());
                expenseData.editExpenseRecord(expense);
                editExpenseView.displayStatus();
                break;
            }
        }
    }

    public void deleteExpense(ArrayList<Expense> expenseList) throws Exception {
        Helper helper = new Helper();
        DeleteExpenseView deleteExpenseView = new DeleteExpenseView();
        while(true){
            expenseHistoryView.displayExpense(expenseList);
            int recordToBeDeleted = helper.getRecord();
            if(helper.checkValidRecord(recordToBeDeleted, expenseList.size())){
                expenseData.deleteExpenseRecord(expenseList.get(recordToBeDeleted-1).getExpenseId());
                deleteExpenseView.deleteStatus();
                break;
            }else{
                deleteExpenseView.displayError();
            }
        }
    }
}