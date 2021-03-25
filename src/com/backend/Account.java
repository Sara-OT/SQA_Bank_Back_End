package com.backend;

public class Account {
    // contains account's current total funds
    public float balance;
    //holds the integer value for the account’s
    // number which is used to identify it
    public int number;
    //determines whether the account has been disabled (D) or
    // is able to make transactions (A)
    public boolean isActive;
    //determines if the account’s plan has been changed to
    // non-student or not
    public boolean isStudentPlan;

    public Account(float balance, int number, boolean isActive, boolean isStudentPlan){
        this.balance = balance;
        this.number = number;
        this.isActive = isActive;
        this.isStudentPlan = isStudentPlan;

    }

    public boolean getAccountStatus(){
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







}
