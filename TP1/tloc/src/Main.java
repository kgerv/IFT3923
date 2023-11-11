import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            File startingFile = new File(args[0]);
            Tloc tloc = new Tloc();
            int tlocValue = tloc.calculate(startingFile);

            System.out.println(tlocValue);
        } else {
            System.out.println("Expecting 1 argument, none received");
        }
    }
}
