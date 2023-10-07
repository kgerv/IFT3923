import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            File startingFile = new File(args[0]);
            Tassert tassert = new Tassert();
            int tassertValue = tassert.calculate(startingFile);

            System.out.println(tassertValue);
        } else {
            System.out.println("Expecting 1 argument, none received");
        }
    }
}
