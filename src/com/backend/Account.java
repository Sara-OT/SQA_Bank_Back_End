package com.backend;

public class Account {

    //holds the integer value for the account’s number which is used to identify it
    int number;

    // Holds the name of the account holder
    private String name;

    // The first character determines whether the account has been disabled (D) or is able to make transactions (A)
    // The second character determines whether the account is a student account (S) or a normal account (N)
    private String isActive;

    // contains account's current total funds
    private float balance;

    // Holds the number of transactions performed on the account
    private int transactionCount;

    //determines if the account’s plan has been changed to
    // non-student or not
    private boolean isStudentPlan;

    public Account(int number, String name, String isActive, float balance, int transactionCount){
        this.number = number;
        this.name = name;
        this.isActive = isActive;
        this.balance = balance;
        this.transactionCount = transactionCount;

        // this.isStudentPlan = isStudentPlan;

    }

    public String getAccountStatus(){
       return this.isActive;
        //return isActive ? "Active" : "Disabled";
    }

    public boolean getStudentPlan(){
        return this.isStudentPlan;
    }

    public float getBalance(){
        return this.balance;
    }
    public int getNumber(){
        return this.number;
    }
    public String getName(){
        return this.name;
    }
    public int getTransactions(){
        return this.transactionCount;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentPlan(boolean studentPlan) {
        isStudentPlan = studentPlan;
    }
}
