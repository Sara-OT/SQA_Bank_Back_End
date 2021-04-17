package com.backend;
import java.io.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    static void mergeFiles(String dirToMerge, String outputFile) throws IOException {
        // Directory to loop through (replace with your own to test)
        File dir = new File(dirToMerge);

        // Output file is created in \\SQA_Bank_Back_End
        PrintWriter writer = new PrintWriter(outputFile);

        // String array for list of files
        String[] fileNames = dir.list();

        // Go through all files in dir
        for (String fileName : fileNames) {
            System.out.println("Reading from " + fileName);

            // Instantiates new file to be used and create a bufferedreader
            File currFile = new File(dir, fileName);
            BufferedReader br = new BufferedReader(new FileReader(currFile));

            // Read from currFile
            String line = br.readLine();
            while (line != null) {
                // Write each line to the output file
                writer.println(line);
                line = br.readLine();
            }
            writer.flush();
        }
        System.out.println("Finished reading from " + dir.getName());
    }
    public static void main(String[] args) throws IOException {
        Path relative = Paths.get("TransactionFiles");
        mergeFiles(
                relative.toString(),
                "Merged_Transactions.txt"
        );

        if (args.length < 3) {
            System.out.println("Usage: java program file1 file2 file3");
            System.exit(0);
        }

        // Command line inputs
        String masterAccounts = args[0];
        String currentAccounts = args[1];
        String sessionTransactions = args[2];

        Bank testing = new Bank();
        testing.allAccounts = testing.readAccounts(masterAccounts);
        testing.allTransactions = testing.readTransactions(sessionTransactions);
        testing.allAccounts = testing.applyTransactions(testing.allAccounts, testing.allTransactions);
        testing.exportNewAccounts(testing.allAccounts, "NewMasterAccounts.txt", currentAccounts);
        
    }
}
