package com.backend;
import java.io.File;
import java.io.FileNotFoundException;
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
        while (scanner.hasNext()) {
            unparsedAccounts.add(scanner.next());
        }
        scanner.close();

        // Looping through every account in unparsedAccounts, parsing the information from each account, and storing
        // the account information in an account object
        for (int i = 0; i < unparsedAccounts.size(); i++) {

            // Creating a new String array for the parsed account information;
            String[] currentAccount = unparsedAccounts.get(i).split(",");

            // Creating a new Account object
            Account newAccount = new Account(currentAccount[0], currentAccount[1], currentAccount[2],
                                             currentAccount[3], currentAccount[4], currentAccount[5]);

            // Adding the account to the list of account objects
            allAccounts.add(newAccount);
        }
    }


}
