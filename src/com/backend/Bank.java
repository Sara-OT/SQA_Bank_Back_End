package com.backend;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Bank {
    private List<Account> allAccounts = new ArrayList<Account>();

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
                                        unparsedAccounts.get(i).substring(30, 38),
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
            String accountNumber = Integer.toString(allAccounts.get(i).getNumber()) + " ";

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
            String accountStatus = allAccounts.get(i).getAccountStatus() + " ";

            // Appending the account status to the accountToString variable
            accountToString += accountStatus;

            // A string for the account number
            String accountBalance = Float.toString(allAccounts.get(i).getBalance());

            // Looping through the remaining characters that need to be zero at the front of the accountBalance string
            for (int j = 0; j < 8 - accountBalance.length(); j++) {
                accountBalance = "0" + accountBalance;
            }

            // Appending the account balance to the accountToString variable with a space an extra space at the end to
            // allow for proper formatting in the Master Bank Accounts File
            accountToString += accountBalance + " ";

            // A string for the account transaction count
            String accountTransactions = Integer.toString(allAccounts.get(i).getTransactions());

            // Looping through the remaining characters that need to be zero at the front of accountTransactions
            for (int j = 0; j < 4 - accountTransactions.length(); j++) {
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

}
