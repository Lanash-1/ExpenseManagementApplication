package view;

public class AddExpenseView {

    public void showAddAccount() {
        System.out.println("No accounts linked. Link a account to add Expense");
    }

    public void addExpenseStatus(){
        System.out.println("\n---------- Expense added to your profile ----------\n");
    }

    public void noAccount() {
        System.out.println("Select from available accounts");
    }
}
