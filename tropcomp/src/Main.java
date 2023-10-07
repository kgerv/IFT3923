import java.io.File;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.FileSystemNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        args = new String[] {"-o", "C:\\Users\\Killian\\Desktop\\Partie5-10.csv", "C:\\Users\\Killian\\Desktop\\jfreechart-master", "10"};
        if(args.length > 0) {
            Tropcomp tropcomp = new Tropcomp();
            try {
                if (args.length == 2) {
                    File startingFile = new File(args[0]);
                    assert startingFile.exists();
                    int threshold = Integer.parseInt(args[1]);
                    tropcomp = new Tropcomp(startingFile, threshold);
                    tropcomp.findSuspectClasses();
                } else if (args[0].compareTo("-o") == 0) {
                    File startingFile = new File(args[2]);
                    assert startingFile.exists();

                    CreateCSV createCSV = new CreateCSV();
                    File csv = new File(args[1]);

                    int threshold = Integer.parseInt(args[3]);
                    tropcomp = new Tropcomp(startingFile, threshold);

                    List<List<String>> output = tropcomp.findSuspectClasses();
                    createCSV.convertToCSV(csv, output);
                }
            } catch(FileSystemNotFoundException e) {
                System.out.println("The system cannot find the specified path");
                e.printStackTrace();
            }
            System.out.println(tropcomp.toString());
        } else {
            System.out.println("Expecting 2 or 4 arguments, none received");
        }
    }
}
