import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String[]> tlsValues;
        String file_path = args[0];
        Tls tls = new Tls(file_path);
        tlsValues = tls.exploreLevel();

        System.out.println(tlsValues);
    }
}
