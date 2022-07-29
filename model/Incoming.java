package model;

public class Incoming {

    private double amount;

    private int incomingId;

    private LinkedAccount account;

    public void setAmount(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return amount;
    }

    public void setAccount(LinkedAccount account){
        this.account = account;
    }

    public LinkedAccount getAccount(){
        return account;
    }

    public void setIncomingId(int incomingId){
        this.incomingId = incomingId;
    }

    public int getIncomingId(){
        return incomingId;
    }

}
