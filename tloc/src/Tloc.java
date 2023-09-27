import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tloc {
    public Tloc() {}

    public int calculateTloc(String path) {
        int line_count = 0;
        int com_line_count = 0;
        int value;

        try {
            File file = new File(path);
            Scanner scan = new Scanner(file);
            boolean inMultiLineCom = false;

            while(scan.hasNextLine()) {
                String line = scan.nextLine();

                if(line.matches("\\s*")) continue;

                boolean isOneLineCom = line.startsWith("//");
                boolean isMultLineStartCom = line.startsWith("/*");
                boolean isMultLineMidCom = line.startsWith("*");
                boolean isMultLineEndCom = line.startsWith("*/") || line.endsWith("*/");

                if(isOneLineCom) ++com_line_count;

                if(isMultLineStartCom) {
                    if(!isMultLineEndCom) inMultiLineCom = true;
                    ++com_line_count;
                }

                if(inMultiLineCom){ //if(isMultLineMidCom && inMultiLineCom) {
                    if(isMultLineEndCom) inMultiLineCom = false;
                    ++com_line_count;
                }

                if(isMultLineEndCom && inMultiLineCom) {
                    inMultiLineCom = false;
                    ++com_line_count;
                }

                ++line_count;
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        value = line_count - com_line_count;

        return value;
    }
}
