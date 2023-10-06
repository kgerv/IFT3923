import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //File startingFile = new File(args[0]);
        File startingFile = new File("C:\\Users\\Killian\\Desktop\\jfreechart-master");
        //int threshold = Integer.parseInt(args[1]);
        int threshold = Integer.parseInt("10");
        Tropcomp tropcomp = new Tropcomp(startingFile, threshold);
        tropcomp.findSuspectClasses();

        System.out.println(tropcomp.toString());
    }
}
