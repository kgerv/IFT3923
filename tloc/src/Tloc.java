import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tloc {
    public Tloc() {}
/*
* asdas
 */
    public int calculateTloc(String path) {
        int line_count = 0;

        try {
            File file = new File(path);
            Scanner scan = new Scanner(file);
            boolean inMultiLineCom = false;

            while(scan.hasNextLine()) {
                String line = scan.nextLine();

                if(line.matches("\\s*")) continue; //empty line
                if(line.matches("\\s*//.*")) continue; //single line comment
                //start of multiline comments
                if(line.matches("\\s*/\\*.*")) {
                    if(line.matches(".*\\*/\\s*")) continue; //ends on same line as start
                    inMultiLineCom = true;
                }
                if(inMultiLineCom) {
                    if(line.matches(".*\\*/\\s*")) { //multiline comments ends on that line
                        inMultiLineCom = false;
                        continue;
                    }
                    continue;
                }
                if(line.matches(".*\\*/\\s*")) continue; //end of multiline comments

                ++line_count;
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return line_count;
    }
}
