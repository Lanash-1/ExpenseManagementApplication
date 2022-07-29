package interfaces;

import controller.ProfileController;
import model.Expense;

import java.util.ArrayList;

public interface ExpenseServices {

    void addExpense(Expense expense, ProfileController profile) throws Exception;

    ArrayList<Expense> getExpenseHistory(String userId) throws Exception;

    double getTotalExpense(String userId) throws Exception;

    void deleteExpenseRecord(int expenseId) throws Exception;

    void editExpenseRecord(Expense expense) throws Exception;
}
