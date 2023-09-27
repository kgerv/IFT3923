import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tassert {
    public Tassert() {}

    public int calculate(String path) {
        int nb_assert = 0;

        try {
            File file = new File(path);
            Scanner scan = new Scanner(file);
            boolean inMultiLineDec = false;
            boolean assertImported = false;

            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                // JUnit Assert package import fully or in part
                if(line.matches(".*import static{0,1} org\\.junit\\.Assert\\..+;.*")) assertImported = true;
                // at least one assert on this line
                // split line in substtring to check if multiple occurence on same line
                if(line.matches("\\s*(\\w*\\W+)*assert[A-Z]\\w+\\(.*\\)\\s*;.*")) {
                    ++nb_assert;
                    System.out.println(line);
                }
                // at least one fail() on this line
                if(line.matches("\\s*(\\w*\\W+)*fail\\w+\\(.*\\)\\s*;.*")) {
                    ++nb_assert;
                    System.out.println(line);
                }
            }
            
            scan.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return nb_assert;
    }
}
