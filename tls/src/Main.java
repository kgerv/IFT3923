import java.io.File;

public class Main {
    public static void main(String[] args) {
        File startingFile = new File(args[0]);
        Tls tls = new Tls(startingFile);
        tls.exploreLevel();

        System.out.println(tls.toString());
    }
}
