import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// Conversion to CSV modified from: https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/
public class CreateCSV {

    public CreateCSV() {}

    public static void convertToCSV(File csv, List<List<String>> data) {
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(csv);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            for(List<String> line : data) {
                writer.writeNext(line.toArray(new String[6]));
            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Error while writing CSV file.");
            e.printStackTrace();
        }
    }

}
