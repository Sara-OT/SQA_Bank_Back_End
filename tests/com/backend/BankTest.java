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
        accounts = Bank.readAccounts("tests\\TestFiles\\transactiontest1accounts.txt");
        testTransactions = Bank.readTransactions("tests\\TestFiles\\transactiontest1.txt");
        FinalAccounts = Bank.readAccounts("tests\\TestFiles\\transactiontest1finalaccounts.txt");
        equivalencyTest = testing.exportNewAccounts(testing.applyTransactions(accounts, testTransactions), "tests\\TestFiles\\test1export.txt");
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

    @Test
    public void readTransactionsLoopZero() throws FileNotFoundException {
        Bank testing = new Bank();
        List<Transaction> loopTransactions = new ArrayList<Transaction>();
        List<Transaction> testLoop = new ArrayList<Transaction>();
        testLoop = testing.readTransactions("tests\\TestFiles\\readTransactionLoopZero.txt");
        Iterator<Transaction> testLoopiterator = testLoop.iterator();
        Iterator<Transaction> loopTransactionsiterator = loopTransactions.iterator();
        Assert.assertEquals(loopTransactions.size(), testLoop.size());
        while(testLoopiterator.hasNext() && loopTransactionsiterator.hasNext()){
            Transaction t1 = testLoopiterator.next();
            Transaction t2 = loopTransactionsiterator.next();
            Assert.assertEquals(t1.getTransactionType(), t2.getTransactionType());
            
        }
        
    }
    @Test 
    public void readTransactionsLoopOne() throws FileNotFoundException{
        Bank testing = new Bank();
        List<Transaction> loopTransactions = new ArrayList<Transaction>();
        List<Transaction> testLoop = new ArrayList<Transaction>();
        Transaction transaction = new Transaction("02 ",
                                                   "transfertest         ",
                                                    "00009 ",
                                                        "00005000 ",
                                                        "  ");
        loopTransactions.add(transaction);
        testLoop = testing.readTransactions("tests\\TestFiles\\readTransactionLoopOne.txt");
        Iterator<Transaction> testLoopiterator = testLoop.iterator();
        Iterator<Transaction> loopTransactionsiterator = loopTransactions.iterator();
        Assert.assertEquals(loopTransactions.size(), testLoop.size());
        while(testLoopiterator.hasNext() && loopTransactionsiterator.hasNext()){
            Transaction t1 = testLoopiterator.next();
            Transaction t2 = loopTransactionsiterator.next();
            Assert.assertEquals(t1.getTransactionType(), t2.getTransactionType());
            Assert.assertEquals(t1.getAccountName(), t2.getAccountName());
            Assert.assertEquals(t1.getAcctNumber(), t2.getAcctNumber());
            Assert.assertEquals(t1.getFunds(), t2.getFunds());
            Assert.assertEquals(t1.getMisc(), t2.getMisc());
        }
    }
    
    @Test
    public void readTransactionsLoopTwo() throws FileNotFoundException{
        Bank testing = new Bank();
        List<Transaction> loopTransactions = new ArrayList<Transaction>();
        List<Transaction> testLoop = new ArrayList<Transaction>();
        for(int i = 0; i < 2; i++) {
            Transaction transaction = new Transaction("02 ",
                    "transfertest         ",
                    "00009 ",
                    "00005000 ",
                    "  ");
            loopTransactions.add(transaction);
        }
        
        testLoop = testing.readTransactions("tests\\TestFiles\\readTransactionLoopTwo.txt");
        Iterator<Transaction> testLoopiterator = testLoop.iterator();
        Iterator<Transaction> loopTransactionsiterator = loopTransactions.iterator();
        Assert.assertEquals(loopTransactions.size(), testLoop.size());
        while(testLoopiterator.hasNext() && loopTransactionsiterator.hasNext()){
            Transaction t1 = testLoopiterator.next();
            Transaction t2 = loopTransactionsiterator.next();
            Assert.assertEquals(t1.getTransactionType(), t2.getTransactionType());
            Assert.assertEquals(t1.getAccountName(), t2.getAccountName());
            Assert.assertEquals(t1.getAcctNumber(), t2.getAcctNumber());
            Assert.assertEquals(t1.getFunds(), t2.getFunds());
            Assert.assertEquals(t1.getMisc(), t2.getMisc());
        }
    }
}

