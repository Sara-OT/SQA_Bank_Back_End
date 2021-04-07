package MergeTests;
import java.io.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Merge {

    private String dirToMerge;
    private String outputFile;

    public Merge(String dirToMerge, String outputFile) {
        this.dirToMerge = dirToMerge;
        this.outputFile = outputFile;

    }
    public String mergeFiles(String dirToMerge, String outputFile) throws IOException {
        // Directory to loop through (replace with your own to test)
        File dir = new File(dirToMerge);

        // Output file is created in \\SQA_Bank_Back_End
        PrintWriter writer = new PrintWriter(outputFile);

        // String array for list of files
        String[] fileNames = dir.list();

        if (fileNames == null) {
            return "ERROR! No files to merge.";
        } else {
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
            return outputFile;
        }


    }
//    public static void main(String[] args) throws IOException {
//        Path relative = Paths.get("TransactionFiles");
//        Merge m = new Merge(relative.toString(), "Merged_Transaction.txt");
//        m.mergeFiles(m.dirToMerge, m.outputFile);
//    }
}
