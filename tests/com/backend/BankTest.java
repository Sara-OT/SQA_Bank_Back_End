package com.backend;

import org.junit.Assert;
import org.junit.Test;
import org.hamcrest.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BankTest {
    @Test 
    public void applyTransactionTest() throws IOException {
        List<Account> accounts = new ArrayList<Account>();
        List<Account> FinalAccounts = new ArrayList<Account>();
        List<Transaction> testTransactions = new ArrayList<Transaction>();
        List<Account> equivalencyTest = new ArrayList<Account>();
        
        Bank testing = new Bank();
        accounts = Bank.readAccounts("transactiontest1accounts.txt");
        testTransactions = Bank.readTransactions("transactiontest1.txt");
        FinalAccounts = Bank.readAccounts("transactiontest1finalaccounts.txt");
        equivalencyTest = testing.exportNewAccounts(testing.applyTransactions(accounts, testTransactions), "test1export.txt");
        Iterator<Account> finalIterator = FinalAccounts.iterator();
        Iterator<Account> equivIterator = equivalencyTest.iterator();
        while(finalIterator.hasNext() && equivIterator.hasNext()){
            Account a1 = finalIterator.next();
            Account a2 = equivIterator.next();
            Assert.assertEquals(a1.number, a2.number);
            Assert.assertEquals(a1.name, a2.name);
            Assert.assertEquals(a1.isActive, a2.isActive);
            Assert.assertEquals(a1.balance, a2.balance, 0.0000001d);
            
        }
        
    }
    
}
