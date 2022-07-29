package interfaces;

import model.Expense;
import model.Incoming;

import java.util.ArrayList;

public interface PrintService {
    void printIncomingHistory(ArrayList<Incoming> incoming);
    void printExpenseHistory(ArrayList<Expense> expense);
}
