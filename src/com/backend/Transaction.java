/*
 Transaction Class: Phase 4

 */
package com.backend;
import com.sun.istack.internal.Nullable;

public class Transaction {
  /* The transaction type associated with the transaction
     01 - withdrawal
     02 - transfer
     03 - paybill
     04 - deposit
     05 - create
     06 - delete
     07 - disable
     08 - changeplan
     00 - end of session
   */
  String transactionType;

  // The account name associated with the transaction
  String accountName;

  // The account number associated with the transaction
  String acctNumber;

  // Funds involved in the transaction
  String funds;

  // Misc values at the end of the transaction line (can be null!)
  String misc;
  public static boolean isNumeric(String str){
    try {
      Double.parseDouble(str);
      return true;
    }catch(NumberFormatException e){
      return false;
    }
  }

  // Hard coded splicer for transaction handling
  public static String[] spliceTransaction(String transaction){

    String transactionCode = transaction.substring(0, 3);

    String accountName = transaction.substring(3, 24);

    String accountNumber = transaction.substring(24, 30);

    String funds = transaction.substring(30, 39);

    @Nullable String misc = transaction.substring(39, 41);
    //System.out.println(transactionCode);
    //System.out.println(accountName);
    //System.out.println(accountNumber);
    //System.out.println(funds);
    //System.out.println(misc);

    String[] splicedTransaction = {transactionCode, accountName, accountNumber, funds, misc};
    return splicedTransaction;
  }



  public boolean validateTransaction(String transactionCode, String accountHolderName, String acctNumber, String funds, @Nullable String misc){
    try{
      return  transactionCode.length() + accountHolderName.length() + acctNumber.length() + funds.length() + misc.length() == 41 &&
              isNumeric(transactionCode) &&
              isNumeric(acctNumber) &&
              isNumeric(funds);
    }catch(Exception e){
      System.out.println(e);
    }
    return false;
  }
  public Transaction(String transactionType, String accountName, String acctNumber, String funds, @Nullable String misc){
    this.transactionType = transactionType;
    this.accountName = accountName;
    this.acctNumber = acctNumber;
    this.funds = funds;
    this.misc = misc;
  }

  // Getters(?)
  public String getTransactionType(){
    return transactionType;
  }
  public String getAccountName(){
    return accountName;
  }
  public String getAcctNumber(){
    return acctNumber;
  }
  public String getFunds(){
    return funds;
  }
  public String getMisc(){
    return misc;
  }

  // Setters(?)
  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public void setAcctNumber(String acctNumber) {
    this.acctNumber = acctNumber;
  }

  public void setFunds(String funds) {
    this.funds = funds;
  }

  public void setMisc(@Nullable String misc) {
    this.misc = misc;
  }
}