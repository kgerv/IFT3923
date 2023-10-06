import java.io.File;

public class Main {
    public static void main(String[] args) {
        File startingFile = new File(args[0]);
        Tassert tassert = new Tassert();
        int tassertValue = tassert.calculate(startingFile);

        System.out.println(tassertValue);
    }
}
