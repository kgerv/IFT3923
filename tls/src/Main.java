import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //File startingFile = new File(args[0]);
        File startingFile = new File("C:\\Users\\Killian\\Desktop\\jfreechart-master\\src\\test\\java\\org\\jfree\\data\\gantt");
        Tls tls = new Tls(startingFile);
        tls.exploreLevel();

        System.out.println(tls.toString());
    }
}
