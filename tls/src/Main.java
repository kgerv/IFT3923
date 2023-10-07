import java.io.File;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.FileSystemNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if(args.length > 0) {
            Tls tls = new Tls();
            try {
                if(args.length == 1) {
                    File startingFile = new File(args[0]);
                    tls = new Tls(startingFile);
                    tls.exploreLevel();
                } else if(args[0].compareTo("-o") == 0) {
                    File startingFile = new File(args[2]);
                    tls = new Tls(startingFile);
                    try {
                        CreateCSV createCSV = new CreateCSV();
                        File csv = new File(args[1]);
                        assert csv.exists();

                        List<List<String>> output = tls.exploreLevel();
                        createCSV.convertToCSV(csv, output);
                    } catch(FileSystemAlreadyExistsException e) {
                        System.out.println("Error while creating CSV file");
                        e.printStackTrace();
                    }
                }
            } catch(FileSystemNotFoundException e) {
                System.out.println("The system cannot find the specified path");
                e.printStackTrace();
            }
            System.out.println(tls.toString());
        } else {
            System.out.println("Expecting 1 or 3 arguments, none received");
        }
    }
}
