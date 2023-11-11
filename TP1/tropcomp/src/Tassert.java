import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tassert {
    public Tassert() {}

    public int calculate(File path) {
        int nbAssert = 0;

        try {
            File file = path;
            Scanner scan = new Scanner(file);
            boolean assertImported = false;

            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                // JUnit Assert package import fully or in part, no checks for specific imports
                if(line.matches(".*import static? org\\.junit\\..*\\.Assert(.*)\\..+;.*")) assertImported = true;

                if(assertImported) {
                    // at least one assert on this line
                    // split line in substring to check if multiple occurrences on same line
                    if(line.matches("\\s*(\\w*\\W+)*assert[A-Z]\\w+\\s*\\(.*\\)\\s*;.*")) {
                        ++nbAssert;;
                    }
                    // at least one fail() on this line
                    if(line.matches("\\s*(\\w*\\W+)*fail\\s*\\(.*\\)\\s*;.*")) {
                        ++nbAssert;;
                    }
                    // multiline fail(), case only fail(
                    if(line.matches("\\s*(\\w*\\W+)*fail\\s*\\(\\s*")) {
                        ++nbAssert;;
                    }
                } else {
                    if(line.matches("\\s*(\\w*\\W+)*Assert.assert[A-Z]\\w+\\s*\\(.*\\)\\s*;.*")) {
                        ++nbAssert;
                    }
                    // at least one fail() on this line
                    if(line.matches("\\s*(\\w*\\W+)*Assert.fail\\s*\\(.*\\)\\s*;.*")) {
                        ++nbAssert;;
                    }
                }
            }
            scan.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return nbAssert;
    }
}
