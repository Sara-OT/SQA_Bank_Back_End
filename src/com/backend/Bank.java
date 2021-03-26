package com.backend;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.text.DecimalFormat;
public class Bank {
    public List<Account> allAccounts = new ArrayList<Account>();
    public static List<Transaction> allTransactions = new ArrayList<Transaction>();
    // This method reads the master bank account file from the previous day and stores all the accounts in a list as
    // account objects
    public void readAccounts(String masterFile) throws FileNotFoundException {

        // This variable unparsedAccounts will contain all the account information in an unparsed string format
        List<String> unparsedAccounts = new ArrayList<String>();

        // This scanner will read through the master account file and insert every account into unparsedAccounts
        Scanner scanner = new Scanner(new File(masterFile));
        while (scanner.hasNextLine()) {
            unparsedAccounts.add(scanner.nextLine());
        }
        scanner.close();

        // Looping through every account in unparsedAccounts, parsing the information from each account, and storing
        // the account information in an account object
        for (int i = 0; i < unparsedAccounts.size(); i++) {

            // Creating a new String array for the parsed account information; since each string from unparsedAccounts
            // has a fixed length of 42 characters, we can get the account information through substrings
            String[] currentAccount = { unparsedAccounts.get(i).substring(0, 5),
                                        unparsedAccounts.get(i).substring(6, 26),
                                        unparsedAccounts.get(i).substring(27, 29),
                                        unparsedAccounts.get(i).substring(29, 38),
                                        unparsedAccounts.get(i).substring(39, 42) };

            // Creating a new Account object
            Account newAccount = new Account(Integer.parseInt(currentAccount[0]), currentAccount[1].trim(),
                                             currentAccount[2], Float.parseFloat(currentAccount[3]),
                                             Integer.parseInt(currentAccount[4]));

            // Adding the account to the list of account objects
            allAccounts.add(newAccount);
        }
    }

    // This method loops through the updated account objects and writes the new information to the new Master Bank
    // Accounts File
    public void exportNewAccounts() throws IOException {

        // Opening the new Master Bank Accounts File to write to
        PrintWriter newMasterAccountsFile = new PrintWriter("NewMasterAccounts.txt", "UTF-8");

        // Looping through each account object in the allAccounts list
        for (int i = 0; i < allAccounts.size(); i++) {

            // A string that will hold all the account information and will be used when writing to the new Master
            // Bank Accounts File
            String accountToString = "";

            // A string for the account number; the length of the account number will always be five, so we only need
            // to add a space at the end
            String accountNumber = Integer.toString(allAccounts.get(i).getNumber());
            for(int j = accountNumber.length(); j < 5; j++){
                accountNumber = "0" + accountNumber;
            }
            accountNumber += " ";
            
            // Appending the account number to the accountToString variable
            accountToString += accountNumber;

            // A string for the account name
            String accountName = allAccounts.get(i).getName();

            // Appending the account name to the accountToString variable
            accountToString += accountName;

            // Looping through the remaining number of characters that need to be spaces in the accountName string to
            // fit the required format for the Master Bank Accounts File. Looping through 21 times instead of 20 to
            // account for the extra space needed at the end
            for (int j = 0; j < 21 - accountName.length(); j++) {
                accountToString += " ";
            }

            // A string for the account status
            String accountStatus = allAccounts.get(i).getAccountStatus();// + " ";

            // Appending the account status to the accountToString variable
            accountToString += accountStatus;

            // A string for the account number
            String accountBalance = Float.toString(allAccounts.get(i).getBalance());

            // Looping through the remaining characters that need to be zero at the front of the accountBalance string
            for (int j = accountBalance.length(); j < 7; j++) {
                accountBalance = "0" + accountBalance;
            }
            if(accountBalance.length() < 8){
                accountBalance += "0";
            }
            

            // Appending the account balance to the accountToString variable with a space an extra space at the end to
            // allow for proper formatting in the Master Bank Accounts File
            accountToString += accountBalance + " ";

            // A string for the account transaction count
            String accountTransactions = Integer.toString(allAccounts.get(i).getTransactions());

            // Looping through the remaining characters that need to be zero at the front of accountTransactions
            for (int j = accountTransactions.length(); j < 4; j++) {
                accountTransactions = "0" + accountTransactions;
            }

            // Appending the account transaction count to the accountToString variable
            accountToString += accountTransactions;

            // Writing the account to the new Master Bank Accounts File
            newMasterAccountsFile.println(accountToString);
        }

        // Closing the new Master Bank Accounts File
        newMasterAccountsFile.close();
    }

    public static void readTransactions(String transactionFile) throws FileNotFoundException {
        List<String> unparsedTransactions = new ArrayList<String>();
        Scanner scanner = new Scanner(new File(transactionFile));
        while (scanner.hasNextLine()) {
            unparsedTransactions.add(scanner.nextLine());
        }
        scanner.close();

        for (int i = 0; i < unparsedTransactions.size(); i++) {

            // Creating a new String array for the parsed account information;
            String[] currentTransaction = Transaction.spliceTransaction(unparsedTransactions.get(i));

            // Creating a new Account object
            Transaction newTransaction = new Transaction(currentTransaction[0], currentTransaction[1], currentTransaction[2],
                    currentTransaction[3], currentTransaction[4]);


            // Adding the account to the list of account objects
            if (newTransaction.validateTransaction(currentTransaction[0], currentTransaction[1], currentTransaction[2],
                    currentTransaction[3], currentTransaction[4])) {
                allTransactions.add(newTransaction);
            } else {
                System.out.print("ERROR: Occurred with following transaction: ");
                System.out.print(currentTransaction[0] + " " + currentTransaction[1] + " " + currentTransaction[2]
                        + " " + currentTransaction[3] + " " + currentTransaction[4] + "\n");
            }

        }

        System.out.println("Finished parsing transactions");
    }
    private static float roundFloat(float f, int places){
        BigDecimal bigDecimal = new BigDecimal(Float.toString(f));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.floatValue();
    }
    public void applyTransactions(List<Account> accounts, List<Transaction> transactions){
        // Setting debits to be applied dependent on whether the account is student or normal plan
        float studentDebit = 0.05f;
        float normalDebit = 0.10f;
        // Looping through each transaction in the transaction list obtained from file
        for(Transaction transaction: transactions){
            // Looping through each account in accounts list obtained from file
            for(int i = 0; i < accounts.size(); i++)/*for(Account account: accounts)*/ {
                // Getting the current account
                Account account = accounts.get(i);
                // The transaction amount specified in the transaction & current balance obtained from account.
                float transactionAmount = Integer.parseInt(transaction.funds.trim());
                float currentBalance = account.getBalance();
                // If the details from the transaction matches the details from the account (name & num)
                if (Integer.parseInt(transaction.acctNumber.trim()) == account.getNumber() && transaction.accountName.trim().equals(account.getName())){
                    // Initially checking if account status is set to disabled
                    if(account.getAccountStatus().trim().equals("D")){
                        System.out.println("ERROR: Account status for account " + account.getNumber() + " " + account.getName() + " is disabled");
                        continue;
                    }
                    /* Checking if transaction is one of:
                       01 - withdrawal
                       02 - transfer
                       03 - paybill
                       Applying transactions which withdraw money from the account
                       If the type matches 01-03 and the balance will not go into the negative range
                       after the transaction has been applied then the transaction occurs.
                     */
                    if((transaction.transactionType.trim().equals("01")
                       || transaction.transactionType.trim().equals("02")
                       || transaction.transactionType.trim().equals("03"))
                       && (currentBalance - transactionAmount - normalDebit) > 0.00
                       && account.getAccountStatus().trim().equals("A")){
                        if(account.getStudentPlan()) {
                            //System.out.println(account.getBalance());
                            //System.out.println(transaction.funds);
                            account.setBalance(roundFloat(currentBalance - transactionAmount - studentDebit, 2));
                        }else{
                            //System.out.println((currentBalance - transactionAmount - normalDebit) > 0.00);
                            //System.out.println(transaction.funds);
                            account.setBalance(roundFloat(currentBalance - transactionAmount - normalDebit, 2));
                        }
                        //System.out.println(roundFloat(account.getBalance(),2));
                        //System.out.println(account.getTransactions());
                        account.setTransactionCount(account.getTransactions()+1);
                    /*Checking if transaction is one of:
                    01 - withdrawal
                    02 - transfer
                    03 - paybill
                    & if the transaction will put the account below a balance of zero (including debiting fee)
                    Will output an error in the console letting you know an insufficient funds error has occurred. 
                    */
                     
                    }else if(transaction.transactionType.trim().equals("01")
                            || transaction.transactionType.trim().equals("02")
                            || transaction.transactionType.trim().equals("03")
                            && (currentBalance - transactionAmount - normalDebit) < 0.00
                            && account.getAccountStatus().equals("A")){
                        System.out.println("ERROR: Insufficient funds to process transaction for " + account.getName()
                                + " " + account.getNumber() + ". Only have " + account.getBalance()
                                + " need " + Float.parseFloat(transaction.funds) + " funds.");
                        continue;
                    }
                    /* The deposit transaction 04
                       Checks if transaction amount plus the current balance will put the balance over maximum specified
                       Also checks if current balance + transaction amount - the debit fee is over 0.00
                       Important for edge cases where say the account has 0.02 current balance and the user deposits 0.01
                       the debiting fee will then put the user into the negative range.
                     */
                    
                    if(transaction.transactionType.trim().equals("04")
                       && (currentBalance + transactionAmount) < 100000
                       && (currentBalance + transactionAmount - normalDebit) > 0.00){
                        if(account.getStudentPlan()) {
                            account.setBalance(roundFloat(account.getBalance() + transactionAmount - studentDebit,2));
                        }else{account.setBalance(roundFloat(account.getBalance() + transactionAmount - normalDebit, 2));}
                        account.setTransactionCount(account.getTransactions()+1);

                    }
                    // 
                    if(transaction.transactionType.trim().equals("05")){
                        System.out.println("ERROR: Account creation failed. Account "
                                + account.getNumber() + " under " + account.getName() + " already exists.");
                        continue;
                    }
                    // Delete transaction, will delete account from list once transaction occurs.
                    if(transaction.transactionType.trim().equals("06")){
                        allAccounts.remove(account);
                        continue;
                    }
                    // Disable account transaction
                    if(transaction.transactionType.trim().equals("07")){
                        account.setIsActive("D ");
                    }
                    if(transaction.transactionType.trim().equals("08")){
                        if(account.getStudentPlan()){
                            account.setStudentPlan(false);
                        }else{
                            account.setStudentPlan(true);
                        }
                    }

                }

            }
        }
    }


}
