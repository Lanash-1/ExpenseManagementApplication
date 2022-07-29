package controller;

import model.Expense;
import utility.ExpenseData;
import utility.Utility;
import view.AddExpenseView;
import view.ExpenseHistoryView;
import view.LinkedAccountsView;

public class ExpenseController {

    private AddExpenseView addExpenseView;

    private ExpenseHistoryView expenseHistoryView;

    private Expense expense;

    private ExpenseData expenseData;

    public ExpenseController() throws Exception {
        addExpenseView = new AddExpenseView();
        expenseHistoryView = new ExpenseHistoryView();
        expense = new Expense();
        expenseData = new ExpenseData();
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
            int index = addExpenseView.getAccount();
            if(profile.getAccounts().size() >= index && index != 0){
                expense.setAccount(profile.getAccounts().get(index-1));
                expense.setAmount(addExpenseView.getAmount());
                expense.setCategory(addExpenseView.getCategory());
                expenseData.addExpense(expense, profile);
                addExpenseView.addExpenseStatus();
            }else{
                addExpenseView.noAccount();
            }
        }else{
            addExpenseView.showAddAccount();
        }
    }
}