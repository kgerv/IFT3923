import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

// Conversion modified from CSV from: https://www.baeldung.com/java-csv
public class CreateCSV {

    public CreateCSV() {}

    public String convertLineToCSV(List<String> data) {
        return data.stream()
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public void convertToCSV(File csv, List<List<String>> list) {
        try {
            PrintWriter pw = new PrintWriter(csv);
            list.stream()
                    .map(this::convertLineToCSV)
                    .forEach(pw::println);
        } catch(IOException e) {
            System.out.println("Error while creating CSV file");
            e.printStackTrace();
        }
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
