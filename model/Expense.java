package model;

public class Expense {
    private double amount;
    private String category;

    private int expenseId;
    private LinkedAccount account;

    public void setAmount(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return amount;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

    public void setAccount(LinkedAccount account){
        this.account = account;
    }

    public LinkedAccount getAccount(){
        return account;
    }

    public void setExpenseId(int expenseId){
        this.expenseId = expenseId;
    }

    public int getExpenseId(){
        return expenseId;
    }
}
