package utility;

import interfaces.PrintService;
import model.Expense;
import model.Incoming;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PrintData implements PrintService {

    public void printIncomingHistory(ArrayList<Incoming> incoming) {
        try {
            File myObj = new File("incomingHistory.txt");
            if (!myObj.createNewFile() && !myObj.delete()) {
                System.out.println("Error while printing.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while printing.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("incomingHistory.txt");
            String text = "S.No\tAccountNumber\tAmount";
            String result;
            myWriter.write(text);
            for (int i = 0; i < incoming.size(); i++) {
                result = "\n"+(i+1)+ ".\t" + incoming.get(i).getAccount().getAccountNumber() + "\t" + incoming.get(i).getAmount();
                myWriter.write(result);
            }
            myWriter.close();
            System.out.println("Successfully printed.");
        } catch (IOException e) {
            System.out.println("An error occurred while printing!");
        }

    }

    public void printExpenseHistory(ArrayList<Expense> expense) {
        try {
            File myObj = new File("expenseHistory.txt");
            if (!myObj.createNewFile() && !myObj.delete()) {
                System.out.println("Error while printing.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while printing.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("expenseHistory.txt");
            String text = "\nS.No\tAccount Number\tAmount\tCategory";
            String result;
            myWriter.write(text);
            for (int i = 0; i < expense.size(); i++) {
                result = "\n"+(i+1)+".\t"+expense.get(i).getAccount().getAccountNumber()+"\t"+expense.get(i).getAmount()+"\t"+expense.get(i).getCategory();
                myWriter.write(result);
            }
            myWriter.close();
            System.out.println("Successfully printed.");
        } catch (IOException e) {
            System.out.println("An error occurred while printing!");
            e.printStackTrace();
        }
    }
}
