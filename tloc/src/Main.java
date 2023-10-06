import java.io.File;

public class Main {
    public static void main(String[] args) {
        File startingFile = new File(args[0]);
        Tloc tloc = new Tloc();
        int tlocValue = tloc.calculate(startingFile);

        System.out.println(tlocValue);
    }
}
