import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File startingFile = new File(args[0]);
        int threshold = Integer.parseInt(args[1]);
        Tropcomp tropcomp = new Tropcomp(startingFile, threshold);
        tropcomp.findSuspectClasses();

        System.out.println(tropcomp.toString());
    }
}
