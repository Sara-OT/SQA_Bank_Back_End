package com.backend;
/* *
* Testing the readTransactions method from bank
* 
* LOOP and DECISION coverage.
* */
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BankTest {
    /*
    * For the loop tests we are actually able to test both the while and for loop simultaneously since one populates
    * the string list and the other parses through the string list -- e.g. if the while loop loops zero times then
    * the for loop will also loop zero times. 
    * 
    * Explicitly however we are testing the for loop. 
    * to clarify the code, the list testLoop is being populated by the method and loopTransactions is
    * being populated in the test with what is expected.
    * 
    * Just to ensure we're both doing loop and decision coverage, I've also added the assertions to ensure we're
    * getting the correct output.
    * 
    * ##This method is pretty simple so we didn't have any test failures.##
    * 
    * Total table:
    * Loop Body    Input:
    * Zero times   Empty transaction file
    * Once         "02 transfertest         00009 00005000   " in transaction file
    * Twice        "02 transfertest         00009 00005000   " times 2 in transaction file
    * Many times   "02 transfertest         00009 00005000   " times N (in our case 11) in transaction file
    * 
    * For this first test we loop zero times. Since we want the readTransactions method to loop zero times
    * we also don't have to populate the loopTransaction list. Since they will both be of size zero.
    * Since both lists are of size zero the while loop in the test will not execute, however I left it in
    * for completeness. 
    * 
    * Loop Body    Input:
    * Zero times   Empty transaction file
    * */
    
    @Test
    public void readTransactionsLoopZero() throws FileNotFoundException {
        List<Transaction> loopTransactions = new ArrayList<Transaction>();
        List<Transaction> testLoop = new ArrayList<Transaction>();
        testLoop = Bank.readTransactions("tests\\TestFiles\\LoopTest\\readTransactionLoopZero.txt");
        Iterator<Transaction> testLoopiterator = testLoop.iterator();
        Iterator<Transaction> loopTransactionsiterator = loopTransactions.iterator();
        Assertions.assertEquals(loopTransactions.size(), testLoop.size());
        while(testLoopiterator.hasNext() && loopTransactionsiterator.hasNext()){
            Transaction t1 = testLoopiterator.next();
            Transaction t2 = loopTransactionsiterator.next();
            Assertions.assertEquals(t1.getTransactionType(), t2.getTransactionType());
            Assertions.assertEquals(t1.getAccountName(), t2.getAccountName());
            Assertions.assertEquals(t1.getAcctNumber(), t2.getAcctNumber());
            Assertions.assertEquals(t1.getFunds(), t2.getFunds());
            Assertions.assertEquals(t1.getMisc(), t2.getMisc());
        }
    }
    /*
    * For this test, we're executing the for loop exactly once.
    * 
    * To do this we feed the readTransactions a file containing one transaction
    * And we populate another list with our expected transaction(s) -- in this case a singular transaction.
    * 
    * We then compare the lists to ensure we have our expected outcome. 
    * 
    *  Loop Body    Input:
    *  Once         "02 transfertest         00009 00005000   " in transaction file
    * */
    
    @Test 
    public void readTransactionsLoopOne() throws FileNotFoundException{
        List<Transaction> loopTransactions = new ArrayList<Transaction>();
        List<Transaction> testLoop = new ArrayList<Transaction>();
        Transaction transaction = new Transaction("02 ",
                                                   "transfertest         ",
                                                    "00009 ",
                                                        "00005000 ",
                                                        "  ");
        loopTransactions.add(transaction);
        testLoop = Bank.readTransactions("tests\\TestFiles\\LoopTest\\readTransactionLoopOne.txt");
        Iterator<Transaction> testLoopiterator = testLoop.iterator();
        Iterator<Transaction> loopTransactionsiterator = loopTransactions.iterator();
        Assertions.assertEquals(loopTransactions.size(), testLoop.size());
        while(testLoopiterator.hasNext() && loopTransactionsiterator.hasNext()){
            Transaction t1 = testLoopiterator.next();
            Transaction t2 = loopTransactionsiterator.next();
            Assertions.assertEquals(t1.getTransactionType(), t2.getTransactionType());
            Assertions.assertEquals(t1.getAccountName(), t2.getAccountName());
            Assertions.assertEquals(t1.getAcctNumber(), t2.getAcctNumber());
            Assertions.assertEquals(t1.getFunds(), t2.getFunds());
            Assertions.assertEquals(t1.getMisc(), t2.getMisc());
        }
    }
    
    /*
     * For this test, we're executing the for loop exactly twice.
     *
     * To do this we feed the readTransactions a file containing one transaction
     * And we populate another list with our expected transaction(s) -- in this case a singular transaction.
     *
     * We then compare the lists to ensure we have our expected outcome.
     *
     *  In keeping with the lecture notes this is
     *
     *  Loop Body    Input:
     *  Twice        "02 transfertest         00009 00005000   " times 2 in transaction file
     * */
    
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
        
        testLoop = Bank.readTransactions("tests\\TestFiles\\LoopTest\\readTransactionLoopTwo.txt");
        Iterator<Transaction> testLoopiterator = testLoop.iterator();
        Iterator<Transaction> loopTransactionsiterator = loopTransactions.iterator();
        Assertions.assertEquals(loopTransactions.size(), testLoop.size());
        while(testLoopiterator.hasNext() && loopTransactionsiterator.hasNext()){
            Transaction t1 = testLoopiterator.next();
            Transaction t2 = loopTransactionsiterator.next();
            Assertions.assertEquals(t1.getTransactionType(), t2.getTransactionType());
            Assertions.assertEquals(t1.getAccountName(), t2.getAccountName());
            Assertions.assertEquals(t1.getAcctNumber(), t2.getAcctNumber());
            Assertions.assertEquals(t1.getFunds(), t2.getFunds());
            Assertions.assertEquals(t1.getMisc(), t2.getMisc());
        }
    }
    /*
     * For this test, we're executing the for loop any number of times greater than twice -- we chose 11.
     *
     * To do this we feed the readTransactions a file containing one transaction
     * And we populate another list with our expected transaction(s) -- in this case a singular transaction.
     *
     * We then compare the lists to ensure we have our expected outcome.
     *
     *  Loop Body    Input:
     *  Many times   "02 transfertest         00009 00005000   " times N (in our case 11) in transaction file
     * */
    @Test
    public void readTransactionsLoopMany() throws FileNotFoundException{
        Bank testing = new Bank();
        List<Transaction> loopTransactions = new ArrayList<Transaction>();
        List<Transaction> testLoop = new ArrayList<Transaction>();
        for(int i = 0; i < 11; i++) {
            Transaction transaction = new Transaction("02 ",
                    "transfertest         ",
                    "00009 ",
                    "00005000 ",
                    "  ");
            loopTransactions.add(transaction);
        }

        testLoop = testing.readTransactions("tests\\TestFiles\\LoopTest\\readTransactionLoopMany.txt");
        Iterator<Transaction> testLoopiterator = testLoop.iterator();
        Iterator<Transaction> loopTransactionsiterator = loopTransactions.iterator();
        Assertions.assertEquals(loopTransactions.size(), testLoop.size());
        while(testLoopiterator.hasNext() && loopTransactionsiterator.hasNext()){
            assertTrue(testLoop.size() > 2);
            Transaction t1 = testLoopiterator.next();
            Transaction t2 = loopTransactionsiterator.next();
            Assertions.assertEquals(t1.getTransactionType(), t2.getTransactionType());
            Assertions.assertEquals(t1.getAccountName(), t2.getAccountName());
            Assertions.assertEquals(t1.getAcctNumber(), t2.getAcctNumber());
            Assertions.assertEquals(t1.getFunds(), t2.getFunds());
            Assertions.assertEquals(t1.getMisc(), t2.getMisc());
        }
    }
    
    
    
    
    
    /* The following test the decision which determines if the transaction is a valid transaction:
    *  Similar to above, we pass valid transactions into the method from a file which then processes the
    *  transactions and puts them into a list which is then returned.
    *  
    *  The table for all tests is as follows:
    *  Decision:    Input:
    *  1 true       "02 transfertest         00009 00005000   "
    *  1 false      "rw transfertest         00009 00005000   "
    * 
    *  2 true       "02 transfertest         00009 00005000   "
    *  2 false      Empty transaction file
    * 
    * 
    *  To keep things simple this test utilizes a test file which contains 2 identical valid transactions
    *  "02 transfertest         00009 00005000   " 
    *  
    *  In keeping with the lecture notes this is 
    * 
    *  Decision:    Input:
    *  1 true       "02 transfertest         00009 00005000   "
    */
    @Test
    public void readTransactionsDecision1True() throws FileNotFoundException {
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
        testLoop = Bank.readTransactions("tests\\TestFiles\\DecisionTest\\decisiontest1true.txt");
        Iterator<Transaction> testLoopiterator = testLoop.iterator();
        Iterator<Transaction> loopTransactionsiterator = loopTransactions.iterator();
        Assertions.assertEquals(loopTransactions.size(), testLoop.size());
        while(testLoopiterator.hasNext() && loopTransactionsiterator.hasNext()){
            Transaction t1 = testLoopiterator.next();
            Transaction t2 = loopTransactionsiterator.next();
            Assertions.assertEquals(t1.getTransactionType(), t2.getTransactionType());
            Assertions.assertEquals(t1.getAccountName(), t2.getAccountName());
            Assertions.assertEquals(t1.getAcctNumber(), t2.getAcctNumber());
            Assertions.assertEquals(t1.getFunds(), t2.getFunds());
            Assertions.assertEquals(t1.getMisc(), t2.getMisc());
        }
    }
    
    /* For this test we pass a transaction line from file that contains a transaction type
    *  that is invalid. In this case we pass "rw" as the transaction type. Since this fails the isNumeric() check
    *  the else is executed. Which outputs the error message and does not add the transaction to the list.  
    * 
    *  To keep things simple this test utilizes a test file which contains 2 identical valid transactions
    *  "02 transfertest         00009 00005000   "
    *  and one invalid
    *  "rw transfertest         00009 00005000   "
     *
    *  In keeping with the lecture notes this is
    *  Decision:    Input:
    *  1 false      "rw transfertest         00009 00005000   "
    */
    @Test 
    public void readTransactionsDecision1False() throws FileNotFoundException {
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
        testLoop = Bank.readTransactions("tests\\TestFiles\\DecisionTest\\decisiontest1false.txt");
        Iterator<Transaction> testLoopiterator = testLoop.iterator();
        Iterator<Transaction> loopTransactionsiterator = loopTransactions.iterator();
        Assertions.assertEquals(loopTransactions.size(), testLoop.size());
        while(testLoopiterator.hasNext() && loopTransactionsiterator.hasNext()){
            Transaction t1 = testLoopiterator.next();
            Transaction t2 = loopTransactionsiterator.next();
            Assertions.assertEquals(t1.getTransactionType(), t2.getTransactionType());
            Assertions.assertEquals(t1.getAccountName(), t2.getAccountName());
            Assertions.assertEquals(t1.getAcctNumber(), t2.getAcctNumber());
            Assertions.assertEquals(t1.getFunds(), t2.getFunds());
            Assertions.assertEquals(t1.getMisc(), t2.getMisc());
        }
    }
    /*
    *  To keep things simple this test utilizes a test file which contains 2 identical valid transactions
    *  "02 transfertest         00009 00005000   "
    *
    *  In keeping with the lecture notes this is
    *  Decision:    Input:
    *  2 true       "02 transfertest         00009 00005000   "
     */
    @Test
    public void readTransactionsDecision2True() throws FileNotFoundException {
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
        testLoop = Bank.readTransactions("tests\\TestFiles\\DecisionTest\\decisiontest2true.txt");
        Iterator<Transaction> testLoopiterator = testLoop.iterator();
        Iterator<Transaction> loopTransactionsiterator = loopTransactions.iterator();
        Assertions.assertEquals(loopTransactions.size(), testLoop.size());
        while(testLoopiterator.hasNext() && loopTransactionsiterator.hasNext()){
            Transaction t1 = testLoopiterator.next();
            Transaction t2 = loopTransactionsiterator.next();
            Assertions.assertEquals(t1.getTransactionType(), t2.getTransactionType());
            Assertions.assertEquals(t1.getAccountName(), t2.getAccountName());
            Assertions.assertEquals(t1.getAcctNumber(), t2.getAcctNumber());
            Assertions.assertEquals(t1.getFunds(), t2.getFunds());
            Assertions.assertEquals(t1.getMisc(), t2.getMisc());
        }
    }
    /*  For this test we pass a transaction file that contains no transactions. Since no transactions are in the file,
     *  the second decision produces false since the size of the produced list must be greater than 0
     *
     *
     *  In keeping with the lecture notes this is
     *  Decision:    Input:
     *  2 false      Empty transaction file
     */
    @Test
    public void readTransactionsDecision2False() throws FileNotFoundException {
        List<Transaction> loopTransactions = new ArrayList<Transaction>();
        List<Transaction> testLoop = new ArrayList<Transaction>();
        // Since we are testing the second decision statement
        // we don't need to populate the list as it should be empty to produce false for
        // the decision. 
        //for(int i = 0; i < 2; i++) {
        //    Transaction transaction = new Transaction("02 ",
        //            "transfertest         ",
        //            "00009 ",
        //            "00005000 ",
        //            "  ");
        //    loopTransactions.add(transaction);
        //}
        testLoop = Bank.readTransactions("tests\\TestFiles\\DecisionTest\\decisiontest2false.txt");
        Iterator<Transaction> testLoopiterator = testLoop.iterator();
        Iterator<Transaction> loopTransactionsiterator = loopTransactions.iterator();
        Assertions.assertEquals(loopTransactions.size(), testLoop.size());
        while(testLoopiterator.hasNext() && loopTransactionsiterator.hasNext()){
            Transaction t1 = testLoopiterator.next();
            Transaction t2 = loopTransactionsiterator.next();
            Assertions.assertEquals(t1.getTransactionType(), t2.getTransactionType());
            Assertions.assertEquals(t1.getAccountName(), t2.getAccountName());
            Assertions.assertEquals(t1.getAcctNumber(), t2.getAcctNumber());
            Assertions.assertEquals(t1.getFunds(), t2.getFunds());
            Assertions.assertEquals(t1.getMisc(), t2.getMisc());
        }
    }
    
}

