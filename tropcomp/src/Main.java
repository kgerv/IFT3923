import java.util.List;

public class Main {
    public static void main(String[] args) {
        String file_path = args[0];
        int threshold = Integer.parseInt(args[1]);
        Tropcomp tropcomp = new Tropcomp(file_path, threshold);
        tropcomp.findSuspectClasses();

        System.out.println(tropcomp.toString());
    }
}
