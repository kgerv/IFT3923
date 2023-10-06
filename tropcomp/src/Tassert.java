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
                // JUnit Assert package import fully or in part, no checks for specific imports
                if(line.matches(".*import static? org\\.junit\\.Assert\\..+;.*")) assertImported = true;

                if(assertImported) {
                    // at least one assert on this line
                    // split line in substring to check if multiple occurrences on same line
                    if(line.matches("\\s*(\\w*\\W+)*assert[A-Z]\\w+\\s*\\(.*\\)\\s*;.*")) {
                        ++nb_assert;;
                    }
                    // at least one fail() on this line
                    if(line.matches("\\s*(\\w*\\W+)*fail\\s*\\(.*\\)\\s*;.*")) {
                        ++nb_assert;;
                    }
                    // multiline fail(), case only fail(
                    if(line.matches("\\s*(\\w*\\W+)*fail\\s*\\(\\s*")) {
                        inMultiLineDec = true;
                        ++nb_assert;;
                    }
                } else {
                    if(line.matches("\\s*(\\w*\\W+)*Assert.assert[A-Z]\\w+\\s*\\(.*\\)\\s*;.*")) {
                        ++nb_assert;
                        System.out.println(line);
                    }
                    // at least one fail() on this line
                    if(line.matches("\\s*(\\w*\\W+)*Assert.fail\\s*\\(.*\\)\\s*;.*")) {
                        ++nb_assert;;
                    }
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
