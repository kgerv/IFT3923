import java.util.List;

public class Main {
    public static void main(String[] args) {
        String file_path = args[0];
        Tls tls = new Tls(file_path);
        tls.exploreLevel();

        System.out.println(tls.toString());
    }
}
